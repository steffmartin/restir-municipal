package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Scp.ScpId.class)
@EqualsAndHashCode(of = {"dirfId", "scpId"})
public class Scp {
    @Id
    Integer scpId;

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

    public class ScpId implements Serializable {
        Integer dirfId;
        Integer scpId;
    }

}
