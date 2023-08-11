package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "dirf_prev_compl")
@EqualsAndHashCode(of = "id")
public class InformacoesPrevCompl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id", nullable = false)
    Beneficiario beneficiario;

    Integer infpcLinha;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_prev_compl_valores",
            joinColumns = {@JoinColumn(name = "prev_compl_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "valor_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cod_registro", length = 7)
    Map<String, Valores> valoresPorRegistro;
}
