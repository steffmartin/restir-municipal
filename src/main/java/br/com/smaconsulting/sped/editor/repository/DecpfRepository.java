package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Decpf;
import br.com.smaconsulting.sped.editor.entity.Dirf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecpfRepository extends JpaRepository<Decpf, String> {
}
