package br.com.saart.entity;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dirf_valores")
@EqualsAndHashCode(of = {"id", "registroLinha"})
@NoArgsConstructor
public class Valores {

    public Valores(Integer linha, String[] campo) {
        this(linha, campo, null, null);
    }

    public Valores(Integer linha, String[] campo, Integer idrecLinha, String idrecCodigo) {
        this.registroLinha = linha;
        this.codRegistro = campo[1];
        this.idrecLinha = idrecLinha;
        this.idrecCodigo = idrecCodigo;
        switch (campo[1]) {
            case "RIL96":
            case "RIPTS":
            case "RIJMRE":
            case "RIRSR": {
                this.tipoValor = TipoValor.ANUAL;
                this.vlrAno = Util.toBigDecimal(campo[2]);
                break;
            }
            case "RIO": {
                this.tipoValor = TipoValor.ANUAL;
                this.vlrAno = Util.toBigDecimal(campo[2]);
                this.descricao = campo[3];
                break;
            }
            case "QTMESES": {
                this.tipoValor = TipoValor.QUANT;
                this.jan = Util.toBigDecimal(campo[2]);
                this.fev = Util.toBigDecimal(campo[3]);
                this.mar = Util.toBigDecimal(campo[4]);
                this.abr = Util.toBigDecimal(campo[5]);
                this.mai = Util.toBigDecimal(campo[6]);
                this.jun = Util.toBigDecimal(campo[7]);
                this.jul = Util.toBigDecimal(campo[8]);
                this.ago = Util.toBigDecimal(campo[9]);
                this.set = Util.toBigDecimal(campo[10]);
                this.out = Util.toBigDecimal(campo[11]);
                this.nov = Util.toBigDecimal(campo[12]);
                this.dez = Util.toBigDecimal(campo[13]);
                break;
            }
            default: {
                this.tipoValor = TipoValor.MENSAL;
                this.jan = Util.toBigDecimal(campo[2]);
                this.fev = Util.toBigDecimal(campo[3]);
                this.mar = Util.toBigDecimal(campo[4]);
                this.abr = Util.toBigDecimal(campo[5]);
                this.mai = Util.toBigDecimal(campo[6]);
                this.jun = Util.toBigDecimal(campo[7]);
                this.jul = Util.toBigDecimal(campo[8]);
                this.ago = Util.toBigDecimal(campo[9]);
                this.set = Util.toBigDecimal(campo[10]);
                this.out = Util.toBigDecimal(campo[11]);
                this.nov = Util.toBigDecimal(campo[12]);
                this.dez = Util.toBigDecimal(campo[13]);
                this.decTer = Util.toBigDecimal(campo[14]);
                break;
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    TipoValor tipoValor;

    Integer registroLinha;

    @Column(length = 7, nullable = false)
    String codRegistro;
    //RTRT
    //RTPO
    //RTPP
    //RTFA
    //RTSP
    //RTEP
    //RTDP
    //RTPA
    //RTIRF
    //CJAC
    //CJAA
    //ESRT
    //ESPO
    //ESPP
    //ESFA
    //ESSP
    //ESEP
    //ESDP
    //ESPA
    //ESIR
    //ESDJ
    //RIP65
    //RIDAC
    //RIIRP
    //RIAP
    //RIMOG
    //RIRPC
    //RIBMR
    //RICAP
    //RISCP
    //RIMUN
    //RISEN
    //DAJUD
    //RIL96 (SOMENTE VLR ANO)
    //RIPTS (SOMENTE VLR ANO)
    //RIJMRE (SOMENTE VLR ANO)
    //RIRSR (SOMENTE VLR ANO)
    //RIO (SOMENTE VLR ANO E DESCRIÇÃO)
    //QTMESES (QUANTITATIVO)

    @ColumnDefault("0")
    BigDecimal jan;
    @ColumnDefault("0")
    BigDecimal fev;
    @ColumnDefault("0")
    BigDecimal mar;
    @ColumnDefault("0")
    BigDecimal abr;
    @ColumnDefault("0")
    BigDecimal mai;
    @ColumnDefault("0")
    BigDecimal jun;
    @ColumnDefault("0")
    BigDecimal jul;
    @ColumnDefault("0")
    BigDecimal ago;
    @ColumnDefault("0")
    BigDecimal set;
    @ColumnDefault("0")
    BigDecimal out;
    @ColumnDefault("0")
    BigDecimal nov;
    @ColumnDefault("0")
    BigDecimal dez;
    @ColumnDefault("0")
    BigDecimal decTer;
    @ColumnDefault("0")
    BigDecimal vlrAno;
    @Column(length = 60)
    String descricao;

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

    public enum TipoValor {
        MENSAL,
        ANUAL,
        QUANT
    }

}
