package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjdec.BpjdecId.class)
@EqualsAndHashCode(of = {"dirfId", "codReceita"})
public class Bpjdec {
    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Declarante declarante;

    @Id
    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjdecId implements Serializable {
        Integer dirfId;
        Integer codReceita;
    }

}
