package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dirf_saude_inf")
@EqualsAndHashCode(of = {"id", "linhaRegistro"})
@NoArgsConstructor
public class PlanoSaudeInfReembolso {

    public PlanoSaudeInfReembolso(Integer linha, String[] campo) {
        this.linhaRegistro = linha;
        this.codRegistro = campo[1];
        this.cpfCnpj = campo[2];
        this.nome = campo[3];
        this.vlrAnoCal = Util.toBigDecimal(campo[4]).movePointLeft(2);
        this.vlrAnosAnt = Util.toBigDecimal(campo[5]).movePointLeft(2);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer linhaRegistro;

    @Column(length = 6, nullable = false)
    String codRegistro;
    //RTPSE
    //RDTPSE

    @Column(length = 14)
    String cpfCnpj;

    @Column(length = 150)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;


}
