package br.com.smaconsulting.sped.editor.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Rdtpse {
    @Id
    String id;

    @Column(length = 14, nullable = false)
    String cpfCnp;

    @Column(length = 150, nullable = false)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;
}
