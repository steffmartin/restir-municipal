package br.com.saart.task.filtros;

public record FiltroOrdemUserData(TipoFlow tipo, Coluna coluna, Operador operador, String valor, Juncao juncao,
                                  Ordem ordem) {
}
