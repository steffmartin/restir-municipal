package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Fci.FciId.class)
@EqualsAndHashCode(of = {"dirfId", "fciId"})
public class Fci {
    @Id
    Integer fciId; //nº da linha

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

    public class FciId implements Serializable {
        Integer dirfId;
        Integer fciId;
    }

}
