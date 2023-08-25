package br.com.saart.view.dirf;

import br.com.saart.entity.Dirf;
import br.com.saart.task.filtros.Coluna;
import br.com.saart.util.Util;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DirfTable {

    public static final List<Coluna> COLUNAS = new ArrayList<>();

    static {
        COLUNAS.add(new Coluna("id", "# ID"));
        COLUNAS.add(new Coluna("declarante.cpfCnpj", "CPF/CNPJ"));
        COLUNAS.add(new Coluna("declarante.nome", "Declarante"));
        COLUNAS.add(new Coluna("anoReferencia", "Ano Referência"));
        COLUNAS.add(new Coluna("anoCalendario", "Ano Calendário"));
        COLUNAS.add(new Coluna("retificadora", "Retificadora"));
        COLUNAS.add(new Coluna("nomeArquivo", "Arquivo"));
        COLUNAS.add(new Coluna("importadoEm", "Importado em"));
    }

    public DirfTable(Dirf dirf) {
        this.dirfId = dirf.getId().toString();
        this.nome = dirf.getDeclarante().getNome();
        this.cpfCnpj = dirf.getDeclarante().getCpfCnpj();
        this.retificadora = dirf.getRetificadora() ? "S" : "N";
        this.importadoEm = Util.toDMY(dirf.getImportadoEm().toLocalDate()) + " " + Util.toHMS(dirf.getImportadoEm().toLocalTime());
        this.anoReferencia = dirf.getAnoReferencia().toString();
        this.anoCalendario = dirf.getAnoCalendario().toString();
        this.nomeArquivo = dirf.getNomeArquivo();
    }

    private String dirfId;
    private String cpfCnpj;
    private String nome;
    private String anoReferencia;
    private String anoCalendario;
    private String retificadora;
    private String nomeArquivo;
    private String importadoEm;

}
