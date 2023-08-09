package br.com.smaconsulting.sped.editor.entity;

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
    @Column(columnDefinition = "serial")
    Integer id;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id")
    Dirf dirf;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_scp_beneficiario",
            joinColumns = {@JoinColumn(name = "scp_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> bpfscpBpjscps;
    //BPFSCP
    //BPJSCP

}
