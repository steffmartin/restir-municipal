package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Infpa.InfpaId.class)
@EqualsAndHashCode(of = {"dirfId", "linhaInfpa"})
public class Infpa {

    @Id
    @Column(name = "dirf_id")
    Integer dirfId; //construtor

    @Id
    @Column(name = "linha_infpa")
    Integer linhaInfpa;

    @Column(length = 6, nullable = false)
    String codRegistroPai; //construtor
    //BPFDEC
    //BPFRRA

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60, nullable = false)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros

    public class InfpaId implements Serializable {
        Integer dirfId;
        Integer linhaInfpa;
    }

}
