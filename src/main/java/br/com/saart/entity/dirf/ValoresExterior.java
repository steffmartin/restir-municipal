package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dirf_valores_ext")
@EqualsAndHashCode(of = {"id", "vrpdeLinha"})
@NoArgsConstructor
public class ValoresExterior {

    public ValoresExterior(Integer linha, String[] campo) {
        this.vrpdeLinha = linha;
        this.dataPgto = Util.parseIsoDate(campo[2]);
        this.codRec = campo[3];
        this.tipoRendimento = Util.toShort(campo[4]);
        this.vlrRendimento = Util.toBigDecimal(campo[5]).movePointLeft(2);
        this.vlrRetido = Util.toBigDecimal(campo[6]).movePointLeft(2);
        this.formaTribut = Util.toShort(campo[7]);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id", nullable = false)
    Beneficiario beneficiario;

    Integer vrpdeLinha;

    LocalDate dataPgto;

    @Column(length = 4)
    String codRec;

    Short tipoRendimento;
    //De acordo com a tabela de informações sobre os rendimentos constante na IN que dispõe sobre a Dirf

    @ColumnDefault("0")
    BigDecimal vlrRendimento;

    @ColumnDefault("0")
    BigDecimal vlrRetido;

    Short formaTribut;
    //De acordo com a tabela de informações sobre a forma de tributação constante na IN que dispõe sobre a Dirf

}
