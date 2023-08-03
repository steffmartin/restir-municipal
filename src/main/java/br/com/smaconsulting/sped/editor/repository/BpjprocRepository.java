package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpfproc;
import br.com.smaconsulting.sped.editor.entity.Bpjproc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BpjprocRepository extends JpaRepository<Bpjproc, String> {
}
