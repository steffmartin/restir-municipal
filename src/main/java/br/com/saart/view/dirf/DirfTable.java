package br.com.saart.view.dirf;

import lombok.Data;

@Data
public class DirfTable {

    private Long id;
    private String cpfCnpj;
    private String nome;
    private String anoReferencia;
    private String anoCalendario;
    private String retificadora;
    private String nomeArquivo;
    private String importadoEm;

}
