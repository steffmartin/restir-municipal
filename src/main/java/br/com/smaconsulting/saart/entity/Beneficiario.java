package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_beneficiario")
@EqualsAndHashCode(of = "id")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    //DADOS BPFDEC, BPJDEC, BPFRRA, BPFSCP, BPJSCP, RPDE, BRPDE, BPFFCI, BPJFCI, BPFPROC, BPJPROC, VPEIM

    Integer benefLinha;

    @Column(length = 7)
    String codigoRegistro;

    @Column(length = 14)
    String cpfCnpj;

    @Column(length = 150)
    String nome;

    LocalDate dataLaudo;

    Boolean alimentado;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<InformacoesAlimentado> infpas;

    Boolean prevCompl;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<InformacoesPrevCompl> infpcs;

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
    Map<String, Valores> valoresPorRegistro;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    Set<ValoresExterior> valoresExterior;
}
