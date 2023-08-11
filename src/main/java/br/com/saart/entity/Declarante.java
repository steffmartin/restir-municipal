package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_declarante")
@EqualsAndHashCode(of = "id")
public class Declarante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "declarante")
    Dirf dirf;

    @ColumnDefault("3")
    Integer decLinha;

    //DADOS DO REGISTRO DECPF, DECPJ

    @Column(length = 14)
    String cpfCnpj;

    @Column(length = 150)
    String nome;

    Boolean socioOstensivo;

    Boolean rendimentoExterior;

    Boolean planoSaude;

    Boolean servicosNotoriais;

    Boolean espolioSaida;

    Short decpfTipoEvento;
    //1 – Encerramento de espólio
    //2 – Saída definitiva do Brasil

    LocalDate dataEspolioSaida;

    Short sitEspolio;
    //0 – Sem espólio
    //1 – Espólio não encerrado

    Boolean falecido;

    LocalDate dataObito;

    @Column(length = 11)
    String cpfInventariante;

    @Column(length = 60)
    String nomeInventariante;

    Short decpjNatureza;
    //0 – Pessoa jurídica de direito privado
    //1 – Órgãos, autarquias e fundações da administração pública federal
    //2 – Órgãos, autarquias e fundações da administração pública estadual, municipal ou do Distrito Federal
    //3 – Empresa pública ou sociedade de economia mista federal
    //4 – Empresa pública ou sociedade de economia mista estadual, municipal ou do Distrito Federal
    //8 – Entidade com alteração de natureza jurídica (uso restrito)

    @Column(length = 11)
    String cpfResponsavelPj;

    Boolean decisaoJudicial;

    Boolean fundoClubeInvestimento;

    Boolean pgtoIsentasImunes;

    Boolean fundPublicaDirPrivado;

    Boolean sitEspecial;

    LocalDate dataSitEspecial;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_declarante_beneficiario",
            joinColumns = {@JoinColumn(name = "declarante_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios;
    //RPDE
    //BRPDE
    //BPFDEC
    //BPJDEC
    //VPEIM se DECPJ
}
