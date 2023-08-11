package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_saude_dep")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeDependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "saude_tit_id", referencedColumnName = "id", nullable = false)
    PlanoSaudeTitular titular;

    Integer dtpseLinha;

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
    @JoinTable(name = "dirf_saude_dep_inf",
            joinColumns = {@JoinColumn(name = "saude_dep_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "saude_inf_id", referencedColumnName = "id")})
    Set<PlanoSaudeInfReembolso> reembolsos;

}
