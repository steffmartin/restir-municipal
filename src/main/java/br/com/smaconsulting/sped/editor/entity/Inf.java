package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Inf.InfId.class)
@EqualsAndHashCode(of = {"dirf", "infId"})
public class Inf {
    @Id
    Integer infId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 500, nullable = false)
    String informacoes;

    public class InfId implements Serializable {
        Dirf dirf;
        Integer infId;
    }

}
