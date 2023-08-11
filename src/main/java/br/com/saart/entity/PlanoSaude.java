package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_saude")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PlanoSaude {

    public PlanoSaude(Integer pseLinha, Integer linha, String[] campo) {
        this.pseLinha = pseLinha;
        this.opseLinha = linha;
        this.cnpj = campo[2];
        this.nome = campo[3];
        this.ans = campo[4];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer pseLinha;

    Integer opseLinha;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @Column(length = 6)
    String ans;

    @OneToMany(mappedBy = "planoSaude", cascade = CascadeType.ALL)
    Set<PlanoSaudeTitular> titulares = new HashSet<>();

    @PrePersist
    private void prePersist() {
        titulares.forEach(tit -> tit.setPlanoSaude(this));
    }

}
