package br.com.saart.view.relatorios;

import br.com.saart.entity.dirf.Declarante;
import br.com.saart.task.filtros.Coluna;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "cpfCnpj")
public class DeclaranteTable {

    public static final List<Coluna> COLUNAS = new ArrayList<>();

    static {
        COLUNAS.add(new Coluna("id", "# ID", Long.class));
        COLUNAS.add(new Coluna("cpfCnpj", "CPF/CNPJ", String.class));
        COLUNAS.add(new Coluna("nome", "Declarante", String.class));
    }

    public DeclaranteTable(Declarante declarante) {
        this.id = declarante.getId().toString();
        this.nome = declarante.getNome();
        this.cpfCnpj = declarante.getCpfCnpj();
    }

    private String id;
    private String cpfCnpj;
    private String nome;

    @Override
    public String toString() {
        return cpfCnpj.concat(" - ").concat(nome);
    }
}
