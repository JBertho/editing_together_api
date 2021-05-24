package fr.esgi.pa.editing_together_api.unit.snippets;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.UpdateSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.DeleteSnippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.UpdateSnippet;
import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateSnippetTest {

    private final UpdateSnippet updateSnippet;
    private final SnippetDAO mockSnippetDAO;
    private final ProjectDAO mockProjectDAO;

    private User user;
    private UpdateSnippetDTO sut;

    public UpdateSnippetTest() {
        mockSnippetDAO = Mockito.mock(SnippetDAO.class);
        mockProjectDAO = Mockito.mock(ProjectDAO.class);
        updateSnippet = new UpdateSnippet(mockSnippetDAO, mockProjectDAO);

        user = User.builder()
                .id(12L)
                .build();
    }

    @BeforeEach
    public void setup() {
        sut = new UpdateSnippetDTO();
        sut.setId(1);
        sut.setProjectId(5);
        sut.setName("ImportsV1");
        sut.setContent("");
    }

    @Test
    public void should_update_snippet() {
        Snippet mockSnippet = new Snippet();
        mockSnippet.setProjectId(5);


        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(mockSnippet);
        Mockito.when(mockProjectDAO.getProjectById(5)).thenReturn(new Project());
        Mockito.when(mockProjectDAO.findIfProjectUserExist(Mockito.any(), Mockito.any())).thenReturn(true);
        updateSnippet.execute(sut, user);
        Mockito.verify(mockSnippetDAO,Mockito.times(1)).saveSnippet(Mockito.any());

    }

    @Test
    public void should_not_update_if_snippet_not_exist() {
        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> updateSnippet.execute(sut, user));
    }

    @Test
    public void should_not_update_if_user_not_linked_to_snippet_project() {
        Snippet mockSnippet = new Snippet();
        mockSnippet.setProjectId(5);
        Mockito.when(mockSnippetDAO.getSnippetById(1)).thenReturn(mockSnippet);
        Mockito.when(mockProjectDAO.getProjectById(5)).thenReturn(new Project());
        Mockito.when(mockProjectDAO.findIfProjectUserExist(Mockito.any(), Mockito.any())).thenReturn(false);
        assertThrows(ForbiddenException.class, () -> updateSnippet.execute(sut, user));
    }
}
