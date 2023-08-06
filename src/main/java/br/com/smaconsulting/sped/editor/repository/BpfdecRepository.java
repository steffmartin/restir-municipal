package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpfdec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BpfdecRepository extends JpaRepository<Bpfdec, Bpfdec.BpfdecId> {
}
