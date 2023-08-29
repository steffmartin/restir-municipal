package br.com.saart.task.filtros;

public record Coluna(String nome, String label, Class type) {
    @Override
    public String toString() {
        return label;
    }
}
