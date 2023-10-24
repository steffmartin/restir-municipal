package br.com.saart.view.codRec;

import br.com.saart.entity.codRec.CodReceita;
import br.com.saart.task.filtros.Coluna;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CodRecTable {

    public static final List<Coluna> COLUNAS = new ArrayList<>();

    static {
        COLUNAS.add(new Coluna("id", "# ID", String.class));
        COLUNAS.add(new Coluna("descricao", "Descrição do Código da Receita", String.class));
    }

    public CodRecTable(CodReceita codReceita) {
        this.idCodRec = codReceita.getId();
        this.descricao = codReceita.getDescricao();
    }

    private String idCodRec;
    private String descricao;

}
