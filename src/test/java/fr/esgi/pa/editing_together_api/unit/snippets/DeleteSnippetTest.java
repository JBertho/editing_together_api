package fr.esgi.pa.editing_together_api.unit.snippets;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.DeleteSnippet;
import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DeleteSnippetTest {

    private final DeleteSnippet deleteSnippet;
    private final SnippetDAO mockSnippetDAO;
    private final ProjectDAO mockProjectDAO;

    private User user;

    public DeleteSnippetTest() {
        mockSnippetDAO = Mockito.mock(SnippetDAO.class);
        mockProjectDAO = Mockito.mock(ProjectDAO.class);
        deleteSnippet = new DeleteSnippet(mockSnippetDAO, mockProjectDAO);

        user = User.builder()
                .id(12L)
                .build();

    }

    @Test
    public void should_delete_snippet() {
        Snippet mockSnippet = new Snippet();
        mockSnippet.setProjectId(5);

        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(mockSnippet);
        Mockito.when(mockProjectDAO.getProjectById(5)).thenReturn(new Project());
        Mockito.when(mockProjectDAO.findIfProjectUserExist(Mockito.any(), Mockito.any())).thenReturn(true);
        deleteSnippet.execute(1, user);
        Mockito.verify(mockSnippetDAO,Mockito.times(1)).deleteSnippet(1);

    }

    @Test
    public void should_not_delete_if_snippet_not_exist() {
        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> deleteSnippet.execute(1, user));
    }

    @Test
    public void should_not_delete_if_user_not_linked_to_snippet_project() {
        Snippet mockSnippet = new Snippet();
        mockSnippet.setProjectId(5);
        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(mockSnippet);
        Mockito.when(mockProjectDAO.getProjectById(5)).thenReturn(new Project());
        Mockito.when(mockProjectDAO.findIfProjectUserExist(Mockito.any(), Mockito.any())).thenReturn(false);
        assertThrows(ForbiddenException.class, () -> deleteSnippet.execute(1, user));
    }
}
