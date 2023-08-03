package br.com.smaconsulting.sped.editor.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Proc {
    @Id
    String id;

    @Column(nullable = false)
    Short idJustica;
    //1 – Justiça federal
    //2 – Justiça do trabalho
    //3 – Justiça estadual/Distrito Federal

    @Column(length = 20, nullable = false)
    String numProc;

    Short tipoAdv;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    @Column(length = 14)
    String cpfCnpjAdv;

    @Column(length = 150)
    String nomeAdv;

    @ColumnDefault("0")
    BigDecimal vlrAdv;

}
