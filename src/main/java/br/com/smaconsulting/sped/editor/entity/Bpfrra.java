package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Bpfrra.BpfrraId.class)
@EqualsAndHashCode(of = {"bpfrraId", "rra"})
public class Bpfrra {
    @Id
    Integer bpfrraId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "rra_id", referencedColumnName = "rra_id")
    })
    Rra rra;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    @Column(length = 50)
    String natRra;

    LocalDate dataLaudo;

    @Column(nullable = false)
    Boolean alimentado;

    public class BpfrraId implements Serializable {
        Integer bpfrraId;
        Rra rra;
    }

}
