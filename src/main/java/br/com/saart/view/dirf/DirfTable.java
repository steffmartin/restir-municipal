package br.com.saart.view.dirf;

import br.com.saart.entity.Dirf;
import br.com.saart.util.Util;
import lombok.Getter;

@Getter
public class DirfTable {

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
