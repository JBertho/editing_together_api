package fr.esgi.pa.editing_together_api.app.compiler.usecase;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.compiler.domain.CompilerStrategy;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.Factories.CompilerFactory;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.Strategies.CompilerC;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.CompilerUtils;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import lombok.AllArgsConstructor;;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class CompileCode {


    public String execute(DockerCompilation dockerCompilation, List<Snippet> snippetsCode, Project project, User currentUser) throws IOException, InterruptedException {

        CompilerStrategy compiler = CompilerFactory.get(project);

        String sourceCode = CompilerUtils.getCodeFromSnippets(snippetsCode);

        compiler.saveCode(sourceCode, currentUser.getId());
        compiler.copyStaticFile(currentUser.getId());

        String imageName = "compile_in_" + project.getLanguage().name().toLowerCase()  + "_" + new Date().getTime();

        String dockerError = compiler.setupDocker(dockerCompilation, imageName, currentUser.getId());
        if (!dockerError.isEmpty()) {
            return dockerError;
        }
        return compiler.runDocker(dockerCompilation, imageName);
    }
}
