package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjdec.BpjdecId.class)
@EqualsAndHashCode(of = {"bpjdecId", "declarante"})
public class Bpjdec {
    @Id
    Integer bpjdecId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Declarante declarante;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjdecId implements Serializable {
        Integer bpjdecId;
        Declarante declarante;
    }

}
