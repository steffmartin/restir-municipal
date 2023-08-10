package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dirf_reembolso")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeInfReembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    Integer linhaRegistro;

    @Column(length = 6)
    String codRegistro;
    //RTPSE
    //RDTPSE

    @Column(length = 14)
    String cpfCnp;

    @Column(length = 150)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;


}
