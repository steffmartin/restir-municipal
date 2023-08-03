package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Dtpse {
    @Id
    String id;

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

    @Column(nullable = false)
    BigDecimal vlrAno;

}
