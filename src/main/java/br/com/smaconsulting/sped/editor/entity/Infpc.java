package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Infpc.InfpcId.class)
@EqualsAndHashCode(of = {"linhaInfpc", "bpfdec"})
public class Infpc {
    @Id
    Integer linhaInfpc;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_bpfdec", referencedColumnName = "linha_bpfdec")
    })
    Bpfdec bpfdec;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class InfpcId implements Serializable {
        Integer linhaInfpc;
        Bpfdec bpfdec;
    }

}
