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

import java.util.List;
import java.util.Optional;

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
        return null;
    }

    @Override
    public Project getProjectById(int projectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return null;
        }
        return ProjectAdapter.adaptToDomain(optionalProject.get());
    }

    @Override
    public Project getProjectByToken(String projectToken) {
        Optional<ProjectEntity> optionalProject = projectRepository.findByToken(projectToken);
        if (optionalProject.isEmpty()) {
            return null;
        }
        return ProjectAdapter.adaptToDomain(optionalProject.get());
    }
}
