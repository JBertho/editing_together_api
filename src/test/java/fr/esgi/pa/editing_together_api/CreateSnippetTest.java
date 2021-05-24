package fr.esgi.pa.editing_together_api;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.CreateSnippet;

import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CreateSnippetTest {

    private CreateSnippet createSnippet;
    private SnippetDAO mockSnippetDAO;
    private ProjectDAO mockProjectDAO;

    private NewSnippetDTO sut;

    public CreateSnippetTest() {
        mockSnippetDAO = Mockito.mock(SnippetDAO.class);
        mockProjectDAO = Mockito.mock(ProjectDAO.class);
        createSnippet = new CreateSnippet(mockSnippetDAO, mockProjectDAO);
    }

    @BeforeEach
    public void setup() {
        sut = new NewSnippetDTO();
        sut.setName("Partie import");
        sut.setContent("import org.junit.jupiter.api.Test;");
        sut.setProjectId(12);

    }

    @Test
    public void should_return_created_snippet() {
        Mockito.when(mockSnippetDAO.createSnippet(Mockito.any())).thenReturn(15);
        Integer createdSnippetId = createSnippet.execute(sut);
        Mockito.verify(mockSnippetDAO, Mockito.times(1)).createSnippet(Mockito.any());
        assertNotNull(createdSnippetId);
        assertEquals(15, createdSnippetId);
    }

    @Test()
    public void should_not_create_snippet_if_project_Id_not_exist() {
        Mockito.when(mockProjectDAO.getProjectById(12)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> createSnippet.execute(sut));
        Mockito.verify(mockProjectDAO, Mockito.times(1)).getProjectById(12);

    }
}
