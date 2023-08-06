package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpjscp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BpjscpRepository extends JpaRepository<Bpjscp, Bpjscp.BpjscpId> {
}
