package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_declarante")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Declarante {

    public Declarante(Integer linha, String[] campo, Integer anoLayout) {
        this.decLinha = linha;
        this.cpfCnpj = campo[2];
        this.nome = campo[3];
        if ("DECPF".equalsIgnoreCase(campo[1])) {
            this.rendimentoExterior = Util.toBoolean(campo[4]);
            this.servicosNotoriais = Util.toBoolean(campo[5]);
            this.planoSaude = Util.toBoolean(campo[6]);
            if (anoLayout >= 2017) {
                this.socioOstensivo = Util.toBoolean(campo[7]);
                this.sitEspecial = Util.toBoolean(campo[8]);
                this.dataSitEspecial = Util.parseIsoDate(campo[9]);
                this.decpfTipoEvento = Util.toShort(campo[10]);
                if (anoLayout >= 2019) {
                    this.falecido = Util.toBoolean(campo[11]);
                    this.dataObito = Util.parseIsoDate(campo[12]);
                    this.sitEspolio = Util.toShort(campo[13]);
                    this.cpfInventariante = campo[14];
                    this.nomeInventariante = campo[15];
                }
            } else {
                this.sitEspecial = Util.toBoolean(campo[7]);
                this.dataSitEspecial = Util.parseIsoDate(campo[8]);
                this.decpfTipoEvento = Util.toShort(campo[9]);
            }
        } else {
            this.decpjNatureza = Util.toShort(campo[4]);
            this.cpfResponsavelPj = campo[5];
            this.socioOstensivo = Util.toBoolean(campo[6]);
            this.decisaoJudicial = Util.toBoolean(campo[7]);
            this.fundoClubeInvestimento = Util.toBoolean(campo[8]);
            this.rendimentoExterior = Util.toBoolean(campo[9]);
            this.planoSaude = Util.toBoolean(campo[10]);
            if (anoLayout >= 2018) {
                this.pgtoIsentasImunes = Util.toBoolean(campo[11]);
                this.fundPublicaDirPrivado = Util.toBoolean(campo[12]);
                this.sitEspecial = Util.toBoolean(campo[13]);
                this.dataSitEspecial = Util.parseIsoDate(campo[14]);
            } else if (anoLayout == 2017) {
                this.pgtoOlimpiadas = Util.toBoolean(campo[11]);
                this.sitEspecial = Util.toBoolean(campo[12]);
                this.dataSitEspecial = Util.parseIsoDate(campo[13]);
            } else if (anoLayout == 2016) {
                this.pgtoCopa = Util.toBoolean(campo[11]);
                this.pgtoOlimpiadas = Util.toBoolean(campo[12]);
                this.sitEspecial = Util.toBoolean(campo[13]);
                this.dataSitEspecial = Util.parseIsoDate(campo[14]);
            } else {
                this.pgtoCopa = Util.toBoolean(campo[11]);
                this.sitEspecial = Util.toBoolean(campo[12]);
                this.dataSitEspecial = Util.parseIsoDate(campo[13]);
            }
        }
    }

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

    Boolean pgtoCopa;

    Boolean pgtoOlimpiadas;

    Boolean fundPublicaDirPrivado;

    Boolean sitEspecial;

    LocalDate dataSitEspecial;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_declarante_beneficiario",
            joinColumns = {@JoinColumn(name = "declarante_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios = new HashSet<>();
    //RPDE
    //BRPDE
    //BPFDEC
    //BPJDEC
    //VPEIM se DECPJ

}
