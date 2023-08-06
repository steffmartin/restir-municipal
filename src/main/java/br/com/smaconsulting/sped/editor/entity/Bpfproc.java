package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Bpfproc.BpfprocId.class)
@EqualsAndHashCode(of = {"linhaBpfproc", "proc"})
public class Bpfproc {
    @Id
    Integer linhaBpfproc;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_proc", referencedColumnName = "linha_proc")
    })
    Proc proc;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    public class BpfprocId implements Serializable {
        Integer linhaBpfproc;
        Proc proc;
    }

}
