package br.com.saart.entity;

import br.com.saart.util.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_beneficiario")
@EqualsAndHashCode(of = {"id", "benefLinha"})
@NoArgsConstructor
public class Beneficiario {

    public Beneficiario(Integer linha, String[] campo) {
        this(linha, campo, null);
    }

    public Beneficiario(Integer linha, String[] campo, Integer rpdeLinha) {
        this.benefLinha = linha;
        this.codigoRegistro = campo[1];
        if ("BRPDE".equalsIgnoreCase(campo[1])) {
            this.rpdeLinha = rpdeLinha;
            this.brpdeTipo = Util.toShort(campo[2]);
            this.codPais = Util.toShort(campo[3]);
            this.nif = campo[4];
            this.nifDispensado = Util.toBoolean(campo[5]);
            this.nifOpcional = Util.toBoolean(campo[6]);
            this.cpfCnpj = campo[7];
            this.nome = campo[8];
            this.relFonteBenef = Util.toShort(campo[9]);
            this.logradouro = campo[10];
            this.numero = campo[11];
            this.complemento = campo[12];
            this.bairroDist = campo[13];
            this.cep = campo[14];
            this.cidade = campo[15];
            this.estado = campo[16];
            this.fone = campo[17];
        } else {
            this.cpfCnpj = campo[2];
            this.nome = campo[3];
            switch (campo[1]) {
                case "BPFDEC": {
                    this.dataLaudo = Util.parseIsoDate(campo[4]);
                    this.alimentado = Util.toBoolean(campo[5]);
                    this.prevCompl = Util.toBoolean(campo[6]);
                    break;
                }
                case "BPFPROC":
                case "BPFFCI": {
                    this.dataLaudo = Util.parseIsoDate(campo[4]);
                    break;
                }
                case "BPFRRA": {
                    this.rraNatureza = campo[4];
                    this.dataLaudo = Util.parseIsoDate(campo[5]);
                    this.alimentado = Util.toBoolean(campo[6]);
                    break;
                }
                case "BPJSCP":
                case "BPFSCP": {
                    this.participacao = Util.toFloat(campo[4]);
                    break;
                }
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //DADOS BPFDEC, BPJDEC, BPFRRA, BPFSCP, BPJSCP, RPDE, BRPDE, BPFFCI, BPJFCI, BPFPROC, BPJPROC, VPEIM

    Integer benefLinha;

    Integer rpdeLinha;

    @Column(length = 7, nullable = false)
    String codigoRegistro;

    @Column(length = 14)
    String cpfCnpj;

    @Column(length = 150)
    String nome;

    LocalDate dataLaudo;

    Boolean alimentado;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<InformacoesAlimentado> infAlimentados = new HashSet<>();

    Boolean prevCompl;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<InformacoesPrevCompl> infPrevCompls = new HashSet<>();

    @Column(length = 50)
    String rraNatureza;

    Float participacao;

    Short brpdeTipo;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    Short codPais;
    //De acordo com a tabela de código dos países constante na IN que dispõe sobre a Dirf

    @Column(length = 30)
    String nif;

    Boolean nifDispensado;

    Boolean nifOpcional;

    Short relFonteBenef;
    //De acordo com a tabela de informações sobre os beneficiários dos rendimentos constante na IN que dispõe sobre a Dirf

    @Column(length = 60)
    String logradouro;

    @Column(length = 6)
    String numero;

    @Column(length = 25)
    String complemento;

    @Column(length = 20)
    String bairroDist;

    @Column(length = 10)
    String cep;

    @Column(length = 40)
    String cidade;

    @Column(length = 40)
    String estado;

    @Column(length = 15)
    String fone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_beneficiario_valores",
            joinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "valor_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cod_registro", length = 7)
    Map<String, Valores> valoresPorRegistro = new HashMap<>();

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<ValoresExterior> valoresExterior = new HashSet<>();

    @PrePersist
    private void prePersist() {
        infAlimentados.forEach(inf -> inf.setBeneficiario(this));
        infPrevCompls.forEach(inf -> inf.setBeneficiario(this));
        valoresExterior.forEach(valor -> valor.setBeneficiario(this));
    }
}
