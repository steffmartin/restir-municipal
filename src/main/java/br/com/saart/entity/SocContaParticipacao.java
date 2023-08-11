package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_scp")
@EqualsAndHashCode(of = "id")
public class SocContaParticipacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer scpLinha;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_scp_beneficiario",
            joinColumns = {@JoinColumn(name = "scp_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios;
    //BPFSCP
    //BPJSCP

}
