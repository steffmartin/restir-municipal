package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Scp.ScpId.class)
@EqualsAndHashCode(of = {"dirf", "scpId"})
public class Scp {
    @Id
    Integer scpId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class ScpId implements Serializable {
        Dirf dirf;
        Integer scpId;
    }

}
