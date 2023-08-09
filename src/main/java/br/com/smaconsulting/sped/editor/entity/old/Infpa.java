package br.com.smaconsulting.sped.editor.entity.old;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

public class Infpa {

    @Id
    @Column(name = "dirf_id")
    Integer dirfId; //construtor

    @Id
    @Column(name = "linha_infpa")
    Integer linhaInfpa;

    @Column(length = 6)
    String codRegistroPai; //construtor
    //BPFDEC
    //BPFRRA

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros


    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

}
