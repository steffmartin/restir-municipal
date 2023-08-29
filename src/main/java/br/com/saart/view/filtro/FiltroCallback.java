package br.com.saart.view.filtro;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface FiltroCallback {
    void execute(Specification<?> specification, Sort sort);
}
