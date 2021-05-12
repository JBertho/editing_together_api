package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectSqlDAO implements ProjectDAO {

    private final ProjectRepository projectRepository;

    @Override
    public Integer createProject(Project project) {
//        projectRepository.save();
        return null;
    }

    @Override
    public void joinProject(int projectId, int userId) {

    }

    @Override
    public List<Project> getUserProjects(int userId) {
        return null;
    }

    @Override
    public Project getProjectById(int projectId) {
        return null;
    }
}
