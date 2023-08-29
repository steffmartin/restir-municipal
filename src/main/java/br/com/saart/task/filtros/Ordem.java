package br.com.saart.task.filtros;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum Ordem {

    ASC("Crescente", Sort.Direction.ASC),
    DESC("Decrescente", Sort.Direction.DESC);

    private final String label;
    private final Sort.Direction direcao;

    @Override
    public String toString() {
        return label;
    }
}
