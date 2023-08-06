package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjproc.BpjprocId.class)
@EqualsAndHashCode(of = {"linhaBpjproc", "proc"})
public class Bpjproc {
    @Id
    Integer linhaBpjproc;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_proc", referencedColumnName = "linha_proc")
    })
    Proc proc;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjprocId implements Serializable {
        Integer linhaBpjproc;
        Proc proc;
    }

}
