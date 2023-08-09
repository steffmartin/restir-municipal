package br.com.smaconsulting.sped.editor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf")
@EqualsAndHashCode(of = "id")
public class Dirf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    String nomeArquivo;

    LocalDateTime dataHoraImportacao;

    //DADOS REGISTRO DIRF, FIMDIRF

    @ColumnDefault("1")
    Integer dirfLinha;

    Year anoReferencia;

    Year anoCalendario;

    Boolean retificadora;

    @Column(length = 12)
    String numeroRecibo;

    @Column(length = 7)
    String codigoLeiaute;
    // 2023 = ARNZRXP

    Integer fimdirfLinha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id")
    Responsavel responsavel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_declarante", referencedColumnName = "id")
    Declarante declarante;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<FundoClubeInvest> fcis;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Processo> procs;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<RendAcumulados> rras;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<SocContaParticipacao> scps;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<PlanoSaude> pses;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Informacoes> infs;

}
