package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Brpde {
    @Id
    String id;

    @Column(nullable = false)
    Short beneficiario;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    @Column(nullable = false)
    Short codPais;
    //De acordo com a tabela de código dos países constante na IN que dispõe sobre a Dirf

    @Column(length = 30)
    String nif;

    @Column(nullable = false)
    Boolean nifDispensado;

    @Column(nullable = false)
    Boolean nifOpcional;

    @Column(length = 14)
    String cpfCnp;

    @Column(length = 150, nullable = false)
    String nome;

    Short relFonteBenef;
    //De acordo com a tabela de informações sobre os beneficiários dos rendimentos constante na IN que dispõe sobre a Dirf

    @Column(length = 60)
    String logradouro;
    @Column(length = 6)
    String numero;
    @Column(length = 25)
    String complemento;
    @Column(length = 20)
    String bairroDist;
    @Column(length = 10)
    String cep;
    @Column(length = 40)
    String cidade;
    @Column(length = 40)
    String estado;
    @Column(length = 15)
    String fone;

}
