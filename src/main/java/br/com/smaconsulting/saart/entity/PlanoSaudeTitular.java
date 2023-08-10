package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_tpse")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeTitular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "opse_id", referencedColumnName = "id")
    PlanoSaude planoSaude;

    Integer linhaTpse;

    @Column(length = 11)
    String cpf;

    @Column(length = 60)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAno;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_tpse_reembolso",
            joinColumns = {@JoinColumn(name = "tpse_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "reembolso_id", referencedColumnName = "id")})
    Set<PlanoSaudeInfReembolso> reembolsos;

    @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL)
    Set<PlanoSaudeDependente> dependentes;


}
