package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Bpfdec.BpfdecId.class)
@EqualsAndHashCode(of = {"declarante", "codReceita"})
public class Bpfdec {
    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Declarante declarante;

    @Id
    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    @Column(nullable = false)
    Boolean alimentado;

    @Column(nullable = false)
    Boolean prevCompl;

    public class BpfdecId implements Serializable {
        Declarante declarante;
        Integer codReceita;
    }


}
