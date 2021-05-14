package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.ProjectAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectEntity;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectSqlDAO implements ProjectDAO {

    private final ProjectRepository projectRepository;

    @Override
    public Integer createProject(Project project) {
        ProjectEntity newProject = projectRepository.save(ProjectAdapter.adaptToEntity(project));
        return newProject.getId();
    }

    @Override
    public Integer joinProject(int projectId, Long userId) {

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
}
