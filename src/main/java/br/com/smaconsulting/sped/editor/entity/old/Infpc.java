package br.com.smaconsulting.sped.editor.entity.old;

import br.com.smaconsulting.sped.editor.entity.Beneficiario;

import javax.persistence.*;

public class Infpc {
    @Id
    Integer linhaInfpc;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "imp_id", referencedColumnName = "imp_id"),
            @JoinColumn(name = "linha_bpfdec", referencedColumnName = "linha_bpfdec")
    })
    Beneficiario bpfdec;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;


    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

}
