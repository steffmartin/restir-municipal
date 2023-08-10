package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "dirf_infpc")
@EqualsAndHashCode(of = "id")
public class InformacoesPrevCompl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    Integer linhaInfpc;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id")
    Beneficiario beneficiario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_infpc_valores",
            joinColumns = {@JoinColumn(name = "infpc_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "valor_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cod_registro", length = 7)
    Map<String, Valores> valoresPorRegistro;
}
