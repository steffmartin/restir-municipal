package br.com.saart.entity;

import br.com.saart.util.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Dirf {

    public Dirf(String nomeArquivo, Integer linha, String[] campo) {
        this.nomeArquivo = nomeArquivo;
        this.dirfLinha = linha;
        this.anoReferencia = Util.toYear(campo[2]);
        this.anoCalendario = Util.toYear(campo[3]);
        this.retificadora = Util.toBoolean(campo[4]);
        this.numeroRecibo = campo[5];
        this.codigoLeiaute = campo[6];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nomeArquivo;

    LocalDateTime importadoEm;

    //DADOS REGISTRO DIRF, FIMDIRF

    @ColumnDefault("1")
    Integer dirfLinha;

    @Column(nullable = false)
    Year anoReferencia;

    @Column(nullable = false)
    Year anoCalendario;

    Boolean retificadora;

    @Column(length = 12)
    String numeroRecibo;

    @Column(length = 7)
    String codigoLeiaute;
    // 2023 = ARNZRXP

    Integer fimdirfLinha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id", nullable = false)
    Responsavel responsavel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_declarante", referencedColumnName = "id", nullable = false)
    Declarante declarante;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<FundoClubeInvest> fcis = new HashSet<>();

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Processo> procs = new HashSet<>();

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<RendAcumulados> rras = new HashSet<>();

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<SocContaParticipacao> scps = new HashSet<>();

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<PlanoSaude> pses = new HashSet<>();

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Informacoes> infs = new HashSet<>();

    @PrePersist
    private void prePersist() {
        importadoEm = LocalDateTime.now();
        responsavel.setDirf(this);
        declarante.setDirf(this);
        fcis.forEach(fci -> fci.setDirf(this));
        procs.forEach(proc -> proc.setDirf(this));
        rras.forEach(rra -> rra.setDirf(this));
        scps.forEach(scp -> scp.setDirf(this));
        pses.forEach(pse -> pse.setDirf(this));
        infs.forEach(inf -> inf.setDirf(this));
    }

}
