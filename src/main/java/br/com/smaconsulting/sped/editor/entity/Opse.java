package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Opse.OpseId.class)
@EqualsAndHashCode(of = {"dirfId", "opseId"})
public class Opse {
    @Id
    Integer opseId;

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    @Column(length = 6)
    String ans;

    public class OpseId implements Serializable {
        Integer dirfId;
        Integer opseId;
    }

}
