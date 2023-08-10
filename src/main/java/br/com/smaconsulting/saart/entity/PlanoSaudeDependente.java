package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_dtpse")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeDependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "tpse_id", referencedColumnName = "id")
    PlanoSaudeTitular titular;

    Integer linhaDtpse;

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros

    @ColumnDefault("0")
    BigDecimal vlrAno;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_dtpse_reembolso",
            joinColumns = {@JoinColumn(name = "dtpse_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "reembolso_id", referencedColumnName = "id")})
    Set<PlanoSaudeInfReembolso> reembolsos;

}
