package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Idrec.IdrecId.class)
@EqualsAndHashCode(of = {"dirfId", "recId"})
public class Idrec {

    @Id
    Integer dirfId;

    @Id
    Integer recId;

    @Column(length = 4, nullable = false)
    String codReceita;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dec_id")
    Declarante declarante;

    public class IdrecId implements Serializable {
        Integer dirfId;
        Integer recId;
    }
}
