package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = {"dirfId"})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Declarante {

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @OneToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;
}
