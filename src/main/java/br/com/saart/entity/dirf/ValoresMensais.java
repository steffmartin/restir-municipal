package br.com.saart.entity.dirf;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Year;

@Data
@Entity
@Table(name = "dirf_valores_mensais")
@EqualsAndHashCode(of = {"id", "registroLinha", "mes"})
@NoArgsConstructor
public class ValoresMensais {

    public ValoresMensais(Integer registroLinha, Year anoCalendario, Integer mes, String codRegistro, BigDecimal valor,
                          String idrecCodigo, Integer idrecLinha) {
        this.registroLinha = registroLinha;
        this.anoCalendario = anoCalendario;
        this.mes = mes;
        this.codRegistro = codRegistro;
        this.valor = valor;
        this.idrecCodigo = idrecCodigo;
        this.idrecLinha = idrecLinha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_valores_id", referencedColumnName = "id", nullable = false)
    Valores valores;

    Integer registroLinha;

    Year anoCalendario;

    Integer mes;

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

    @ColumnDefault("0")
    BigDecimal valor;

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

}
