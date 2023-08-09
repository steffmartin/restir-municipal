package br.com.smaconsulting.sped.editor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dirf_valores")
@EqualsAndHashCode(of = "id")
public class Valores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    TipoValor tipoValor;

    Integer linhaRegistro;

    @Column(length = 7)
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
