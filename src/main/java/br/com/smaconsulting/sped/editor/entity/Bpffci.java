package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Bpffci.BpffciId.class)
@EqualsAndHashCode(of = {"linhaBpffci", "fci"})
public class Bpffci {

    @Id
    Integer linhaBpffci;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_fci", referencedColumnName = "linha_fci")
    })
    Fci fci;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    public class BpffciId implements Serializable {
        Fci fci;
        Integer linhaBpffci;
    }

}
