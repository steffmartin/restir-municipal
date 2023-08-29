package br.com.saart.specification;

import br.com.saart.task.filtros.Coluna;
import br.com.saart.task.filtros.FiltroOrdemUserData;
import br.com.saart.task.filtros.Juncao;
import br.com.saart.task.filtros.Operador;
import br.com.saart.util.Util;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked,rawtypes")
@Component
public class CustomSpecification<T> {

    public Specification<T> criarSpecification(List<FiltroOrdemUserData> filtro) {
        return (root, query, cb) -> {

            Deque<Predicate> dequePredicate = new LinkedList<>();
            Deque<FiltroOrdemUserData> dequeJuncao = new LinkedList<>();

            FiltroOrdemUserData ultimaColuna = null, ultimaOperacao = null, ultimaJuncao = null;
            Predicate ultimoPredicate = cb.conjunction();

            for (FiltroOrdemUserData token : filtro) {
                switch (token.tipo()) {
                    case COL ->
                            ultimaColuna = token;                                                                                           // Significa que o TOKEN é o nome de uma coluna. Os próximos Tokens definirão o que fazer com esta coluna. Por enquato, basta guardar a coluna.
                    case OPR -> {                                                                                                           //Significa que é uma operação com a coluna do token anterior, guardada previamente.
                        if (Operador.NULL.equals(token.operador()) || Operador.NOT_NULL.equals(token.operador())) {                         //Se for o operador IS NULL ou IS NOT NULL, não haverá um valor no próximo token, então, o Predicate pode ser criado.
                            ultimoPredicate = criarPredicate(ultimoPredicate, ultimaJuncao, root, cb, ultimaColuna, token);
                            ultimaJuncao = null;
                        } else {                                                                                                            //Para os demais operadores, precisamos aguardar o valor que virá no próximo token, então, basta armazenar o operador.
                            ultimaOperacao = token;
                        }
                    }
                    case VLR -> {                                                                                                           //Significa que é o valor que faltava para concluir uma operação. Com este valor o Predicate pode ser criado.
                        ultimoPredicate = criarPredicate(ultimoPredicate, ultimaJuncao, root, cb, ultimaColuna, ultimaOperacao, token);
                        ultimaJuncao = null;
                    }
                    case JUN -> {                                                                                                           //Significa que é uma junção AND ou OR, ou pode ser um parênteses de abertura ou de fechamento.
                        switch (token.juncao()) {
                            case AND, OR ->
                                    ultimaJuncao = token;                                                                                   //No caso de AND ou OR, precisamos aguardar o próximo Predicate para fazer a junção dos dois Predicates. Assim, apenas guardamos a Junção.
                            case OPEN -> {                                                                                                  //Parênteses "(". Colocamos o Predicate e Junção na pilha para usar posteriormente
                                if (ObjectUtils.allNotNull(ultimoPredicate, ultimaJuncao)) {
                                    dequePredicate.push(ultimoPredicate);
                                    ultimoPredicate = null;
                                    dequeJuncao.push(ultimaJuncao);
                                    ultimaJuncao = null;
                                }
                            }
                            case CLOSE -> {                                                                                                 //Parênteses ")". Retiramos a Junção da pilha e fazemos a junção do Predicate que estava na pilha com o atual.
                                if (dequePredicate.size() > 0 && dequeJuncao.size() > 0) {
                                    if (Juncao.AND.equals(dequeJuncao.pop().juncao())) {
                                        ultimoPredicate = cb.and(dequePredicate.pop(), ultimoPredicate);
                                    } else {
                                        ultimoPredicate = cb.or(dequePredicate.pop(), ultimoPredicate);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return ultimoPredicate;
        };
    }

    private Predicate criarPredicate(Predicate ultimoPredicate, FiltroOrdemUserData ultimaLigacao,
                                     Root<T> root, CriteriaBuilder cb, FiltroOrdemUserData... filtro) {

        Coluna coluna = filtro[0].coluna();
        Operador operador = filtro[1].operador();
        String valor = filtro.length == 3 ? filtro[2].valor() : "";

        Predicate novoPredicate = switch (operador) {
            case EQUAL -> cb.equal(getPath(root, coluna), converter(coluna, valor));
            case GREATER -> cb.greaterThan(getPath(root, coluna), (Comparable) converter(coluna, valor));
            case LESS -> cb.lessThan(getPath(root, coluna), (Comparable) converter(coluna, valor));
            case GREATER_EQUAL -> cb.greaterThanOrEqualTo(getPath(root, coluna), (Comparable) converter(coluna, valor));
            case LESS_EQUAL -> cb.lessThanOrEqualTo(getPath(root, coluna), (Comparable) converter(coluna, valor));
            case NOT -> cb.notEqual(getPath(root, coluna), converter(coluna, valor));
            case IN -> getPath(root, coluna).in(converter(coluna, separar(valor)));
            case NOT_IN -> cb.not(getPath(root, coluna).in(converter(coluna, separar(valor))));
            case LIKE -> cb.like(cb.upper(getPath(root, coluna)), "%" + converter(coluna, valor.toUpperCase()) + "%");
            case STARTS -> cb.like(cb.upper(getPath(root, coluna)), converter(coluna, valor.toUpperCase()) + "%");
            case ENDS -> cb.like(cb.upper(getPath(root, coluna)), "%" + converter(coluna, valor.toUpperCase()));
            case NULL -> cb.isNull(getPath(root, coluna));
            case NOT_NULL -> cb.isNotNull(getPath(root, coluna));
        };

        if (ObjectUtils.allNotNull(ultimoPredicate, ultimaLigacao)) {
            if (Juncao.AND.equals(ultimaLigacao.juncao())) {
                return cb.and(ultimoPredicate, novoPredicate);
            } else {
                return cb.or(ultimoPredicate, novoPredicate);
            }
        } else {
            return novoPredicate;
        }
    }

    private List<String> separar(String valor) {
        return Arrays.stream(Util.split(valor)).toList();
    }

    //FIXME preparar mensagem amigável, pois às vezes a conversão não é possível. Por exemplo, usar CONTÉM num campo numérico
    private List<Object> converter(Coluna coluna, List<String> valores) {
        return valores.stream().map(valor -> converter(coluna, valor)).toList();
    }

    private Object converter(Coluna coluna, String valor) {
        return Util.toObj(coluna.type(), valor);
    }

    private Path getPath(Root<T> root, Coluna coluna) {
        Path path = root;
        for (String campo : coluna.nome().split("\\.")) {
            path = path.get(campo);
        }
        return path;
    }

}
