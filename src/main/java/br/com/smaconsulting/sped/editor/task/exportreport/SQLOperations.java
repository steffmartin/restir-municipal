package br.com.smaconsulting.sped.editor.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum SQLOperations {

    NUMERIC(Arrays.asList("Maior ou igual", "Maior", "Igual", "Diferente", "Menor", "Menor ou igual"),
            Arrays.asList("[GREATER", "GREATER", "EQUAL", "NOTEQUAL", "LESS", "LESS]"));

    private final List<String> labels;
    private final List<String> operators;

}

