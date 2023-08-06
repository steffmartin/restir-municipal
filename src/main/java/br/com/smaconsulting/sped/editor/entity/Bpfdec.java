package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@IdClass(Bpfdec.BpfdecId.class)
@EqualsAndHashCode(of = {"linhaBpfdec", "declarante"})
public class Bpfdec {

    @Id
    @Column(name = "linha_bpfdec")
    Integer linhaBpfdec;

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Declarante declarante;

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

    @OneToMany(mappedBy = "bpfdec", cascade = CascadeType.ALL)
    Set<Infpc> infpcs;

    public class BpfdecId implements Serializable {
        Integer linhaBpfdec;
        Declarante declarante;
    }


}
