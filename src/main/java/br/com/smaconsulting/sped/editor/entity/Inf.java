package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Inf.InfId.class)
@EqualsAndHashCode(of = {"dirfId", "infId"})
public class Inf {
    @Id
    Integer infId;

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 500, nullable = false)
    String informacoes;

    public class InfId implements Serializable {
        Integer dirfId;
        Integer infId;
    }

}
