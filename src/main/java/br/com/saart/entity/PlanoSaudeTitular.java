package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_saude_tit")
@EqualsAndHashCode(of = "id")
public class PlanoSaudeTitular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "saude_id", referencedColumnName = "id", nullable = false)
    PlanoSaude planoSaude;

    Integer tpseLinha;

    @Column(length = 11)
    String cpf;

    @Column(length = 60)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAno;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_saude_tit_inf",
            joinColumns = {@JoinColumn(name = "saude_tit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "saude_inf_id", referencedColumnName = "id")})
    Set<PlanoSaudeInfReembolso> reembolsos;

    @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL)
    Set<PlanoSaudeDependente> dependentes;


}
