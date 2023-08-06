package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Tpse {
    @Id
    Integer linhaTpse;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    @Column(nullable = false)
    BigDecimal vlrAno;

}
