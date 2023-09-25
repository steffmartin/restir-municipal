package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_valores")
@EqualsAndHashCode(of = {"id", "registroLinha"})
@NoArgsConstructor
public class Valores {

    public Valores(Year anoCalendario, Integer linha, String[] campo) {
        this(anoCalendario, linha, campo, null, null);
    }

    //TODO geração do relatorio: notificar anos calendario ausentes ou duplicados
    //TODO geração do relatorio: notificar tabela selic incompleta
    public Valores(Year anoCalendario, Integer linha, String[] campo, Integer idrecLinha, String idrecCodigo) {
        this.registroLinha = linha;
        this.codRegistro = campo[1];
        this.idrecLinha = idrecLinha;
        this.idrecCodigo = idrecCodigo;
        this.anoCalendario = anoCalendario;
        switch (campo[1]) {
            case "RIL96", "RIPTS", "RIJMRE", "RIRSR" -> {
                this.tipoValor = TipoValor.ANUAL;
                this.vlrAno = Util.toBigDecimal(campo[2]).movePointLeft(2);
            }
            case "RIO" -> {
                this.tipoValor = TipoValor.ANUAL;
                this.vlrAno = Util.toBigDecimal(campo[2]).movePointLeft(2);
                this.descricao = campo[3];
            }
            case "QTMESES" -> {
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
            }
            default -> {
                this.tipoValor = TipoValor.MENSAL;
                for (int mes = 1; mes <= 13; mes++) {
                    BigDecimal valor = Util.toBigDecimal(campo[mes + 1]).movePointLeft(2);
                    this.valoresMensais.add(new ValoresMensais(linha, anoCalendario, mes, this.codRegistro, valor, idrecCodigo, idrecLinha));
                    switch (mes) {
                        case 1 -> this.jan = valor;
                        case 2 -> this.fev = valor;
                        case 3 -> this.mar = valor;
                        case 4 -> this.abr = valor;
                        case 5 -> this.mai = valor;
                        case 6 -> this.jun = valor;
                        case 7 -> this.jul = valor;
                        case 8 -> this.ago = valor;
                        case 9 -> this.set = valor;
                        case 10 -> this.out = valor;
                        case 11 -> this.nov = valor;
                        case 12 -> this.dez = valor;
                        case 13 -> this.decTer = valor;
                    }
                }
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    TipoValor tipoValor;

    Year anoCalendario;

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

    @OneToMany(mappedBy = "valores", cascade = CascadeType.ALL)
    Set<ValoresMensais> valoresMensais = new HashSet<>();

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

    @PrePersist
    private void prePersist() {
        valoresMensais.forEach(vlr -> vlr.setValores(this));
    }

    public enum TipoValor {
        MENSAL,
        ANUAL,
        QUANT
    }

}
