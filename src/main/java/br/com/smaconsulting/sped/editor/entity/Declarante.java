package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Declarante {
    @Id
    Integer dirfId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "dirf_id")
    Dirf dirf;
}
