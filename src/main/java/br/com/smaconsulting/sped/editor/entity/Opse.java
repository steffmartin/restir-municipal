package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Opse.OpseId.class)
@EqualsAndHashCode(of = {"dirf", "opseId"})
public class Opse {
    @Id
    Integer opseId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    @Column(length = 6)
    String ans;

    public class OpseId implements Serializable {
        Dirf dirf;
        Integer opseId;
    }

}
