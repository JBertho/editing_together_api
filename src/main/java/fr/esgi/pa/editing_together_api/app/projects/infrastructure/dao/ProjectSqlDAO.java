package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.ProjectAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectEntity;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectUserEntity;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectUserId;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories.ProjectRepository;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories.ProjectUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectSqlDAO implements ProjectDAO {

    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;

    @Override
    public Integer createProject(Project project) {
        ProjectEntity newProject = projectRepository.save(ProjectAdapter.adaptToEntity(project));
        return newProject.getId();
    }

    @Override
    public void joinProject(Project project, User user) {
        ProjectUserEntity projectUserEntity = ProjectUserEntity.builder()
                .projectId(project.getId())
                .userId(user.getId())
                .build();

        projectUserRepository.save(projectUserEntity);

    }

    @Override
    public boolean findIfProjectUserExist(Project project, User user) {
        return projectUserRepository.existsById(ProjectUserId.builder().projectId(project.getId()).userId(user.getId()).build());
    }

    @Override
    public List<Project> getUserProjects(Long userId) {
        List<ProjectUserEntity> allByUserId = projectUserRepository.findAllByUserId(userId);
        if (allByUserId.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProjectEntity> projectEntities = projectRepository.findAllById(allByUserId.stream().map(ProjectUserEntity::getProjectId).collect(Collectors.toList()));
        return projectEntities.stream().map(ProjectAdapter::adaptToDomain)
                .peek(project -> project.setParticipants(projectUserRepository.countAllByProjectId(project.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Project getProjectById(int projectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return null;
        }
        Project project = ProjectAdapter.adaptToDomain(optionalProject.get());
        project.setParticipants(projectUserRepository.countAllByProjectId(project.getId()));
        return project;
    }

    @Override
    public Project getProjectByToken(String projectToken) {
        Optional<ProjectEntity> optionalProject = projectRepository.findByToken(projectToken);
        if (optionalProject.isEmpty()) {
            return null;
        }
        Project project = ProjectAdapter.adaptToDomain(optionalProject.get());
        project.setParticipants(projectUserRepository.countAllByProjectId(project.getId()));
        return project;
    }
}
