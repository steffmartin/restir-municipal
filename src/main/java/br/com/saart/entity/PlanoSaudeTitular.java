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
@Table(name = "dirf_saude_tit")
@EqualsAndHashCode(of = {"id", "tpseLinha"})
@NoArgsConstructor
public class PlanoSaudeTitular {

    public PlanoSaudeTitular(Integer linha, String[] campo) {
        this.tpseLinha = linha;
        this.cpf = campo[2];
        this.nome = campo[3];
        this.vlrAno = Util.toBigDecimal(campo[4]);
    }

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
    Set<PlanoSaudeInfReembolso> reembolsos = new HashSet<>();

    @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL)
    Set<PlanoSaudeDependente> dependentes = new HashSet<>();

    @PrePersist
    private void prePersist() {
        dependentes.forEach(dep -> dep.setTitular(this));
    }


}
