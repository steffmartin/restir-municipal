package br.com.saart.task.filtros;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operador {

    EQUAL("Igual a"),
    GREATER("Maior que"),
    LESS("Menor que"),
    GREATER_EQUAL("Maior ou igual a"),
    LESS_EQUAL("Menor ou igual a"),
    NOT("Diferente de"),
    IN("Contido em"),
    NOT_IN("Não contido em"),
    LIKE("Contém"),
    STARTS("Começa com"),
    ENDS("Termina com"),
    NULL("Vazio"),
    NOT_NULL("Não vazio");

    private final String label;

    @Override
    public String toString() {
        return label;
    }
}
