package br.com.saart.task.filtros;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Juncao {

    AND("E"),
    OR("Ou"),
    OPEN("("),
    CLOSE(")");

    private final String label;

    @Override
    public String toString() {
        return label;
    }
}
