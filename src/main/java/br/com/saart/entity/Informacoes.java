package br.com.saart.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "dirf_inf")
@EqualsAndHashCode(of = {"id", "infLinha"})
@NoArgsConstructor
public class Informacoes {

    public Informacoes(Integer linha, String[] campo) {
        this.infLinha = linha;
        this.cpf = campo[2];
        this.informacoes = campo[3];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer infLinha;

    @Column(length = 11)
    String cpf;

    @Column(length = 500)
    String informacoes;

}
