package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.dto.Request;

import lombok.Data;

import java.util.List;

@Data
public class CompileContent {
    List<Integer> snippetsId;
    Integer projectId;
}
