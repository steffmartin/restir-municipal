package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Vpeim.VpeimId.class)
@EqualsAndHashCode(of = {"linhaVpeim", "decpj"})
public class Vpeim {

    @Id
    Integer linhaVpeim;

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id")
    Decpj decpj;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class VpeimId implements Serializable {
        Decpj decpj;
        Integer linhaVpeim;
    }

}
