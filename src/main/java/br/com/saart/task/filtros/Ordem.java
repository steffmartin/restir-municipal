package br.com.saart.task.filtros;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Ordem {

    ASC("Crescente"),
    DESC("Decrescente");

    private final String label;

    @Override
    public String toString() {
        return label;
    }
}
