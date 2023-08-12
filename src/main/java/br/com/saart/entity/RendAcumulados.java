package br.com.saart.entity;

import br.com.saart.util.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_acum")
@EqualsAndHashCode(of = {"id", "rraLinha"})
@NoArgsConstructor
public class RendAcumulados {

    public RendAcumulados(Integer linha, String[] campo) {
        this.rraLinha = linha;
        this.tipoRra = Util.toShort(campo[2]);
        this.numeroRequerimento = campo[3];
        this.tipoAdv = Util.toShort(campo[4]);
        this.cpfCnpjAdv = campo[5];
        this.nomeAdv = campo[6];
        this.vlrAdv = Util.toBigDecimal(campo[7]);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer rraLinha;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Short tipoRra;
    //1 – Pago pelo declarante
    //2 – Pago pela justiça

    @Column(length = 20)
    String numeroRequerimento;

    Short tipoAdv;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    @Column(length = 14)
    String cpfCnpjAdv;

    @Column(length = 150)
    String nomeAdv;

    @ColumnDefault("0")
    BigDecimal vlrAdv;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_acum_beneficiario",
            joinColumns = {@JoinColumn(name = "acum_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios = new HashSet<>();
    //BPFRRA

}
