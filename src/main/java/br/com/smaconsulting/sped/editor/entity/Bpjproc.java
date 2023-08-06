package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjproc.BpjprocId.class)
@EqualsAndHashCode(of = {"bpjprocId", "proc"})
public class Bpjproc {
    @Id
    Integer bpjprocId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "proc_id", referencedColumnName = "proc_id")
    })
    Proc proc;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjprocId implements Serializable {
        Integer bpjprocId;
        Proc proc;
    }

}
