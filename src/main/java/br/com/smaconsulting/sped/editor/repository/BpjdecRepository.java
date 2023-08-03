package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpfdec;
import br.com.smaconsulting.sped.editor.entity.Bpjdec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BpjdecRepository extends JpaRepository<Bpjdec, String> {
}
