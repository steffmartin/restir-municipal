package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(Opse.OpseId.class)
@EqualsAndHashCode(of = {"dirf", "linhaOpse"})
public class Opse {
    @Id
    @Column(name = "linha_opse")
    Integer linhaOpse;

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

    @OneToMany(mappedBy = "opse", cascade = CascadeType.ALL)
    Set<Tpse> tpses;

    public class OpseId implements Serializable {
        Dirf dirf;
        Integer linhaOpse;
    }

}
