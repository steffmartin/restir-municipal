package br.com.saart.task.filtros;

public record Coluna(String coluna, String label) {
    @Override
    public String toString() {
        return label;
    }
}
