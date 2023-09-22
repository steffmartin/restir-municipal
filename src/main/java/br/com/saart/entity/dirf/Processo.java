package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_proc")
@EqualsAndHashCode(of = {"id", "procLinha"})
@NoArgsConstructor
public class Processo {

    public Processo(Integer linha, String[] campo, Integer anoLayout) {
        this.procLinha = linha;
        this.tipoJustica = Util.toShort(campo[2]);
        this.numProc = campo[3];
        this.tipoAdv = Util.toShort(campo[4]);
        this.cpfCnpjAdv = campo[5];
        this.nomeAdv = campo[6];
        if (anoLayout >= 2017) {
            this.vlrAdv = Util.toBigDecimal(campo[7]).movePointLeft(2);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer procLinha;

    Short tipoJustica;
    //1 – Justiça federal
    //2 – Justiça do trabalho
    //3 – Justiça estadual/Distrito Federal

    @Column(length = 20)
    String numProc;

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
    @JoinTable(name = "dirf_proc_beneficiario",
            joinColumns = {@JoinColumn(name = "proc_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios = new HashSet<>();
    //BPFPROC
    //BPJPROC

}
