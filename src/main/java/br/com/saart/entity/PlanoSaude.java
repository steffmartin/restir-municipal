package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_saude")
@EqualsAndHashCode(of = "id")
public class PlanoSaude {

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
    Set<PlanoSaudeTitular> titulares;

}
