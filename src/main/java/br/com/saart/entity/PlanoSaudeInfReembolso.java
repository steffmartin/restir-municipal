package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dirf_saude_inf")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeInfReembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer linhaRegistro;

    @Column(length = 6, nullable = false)
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
