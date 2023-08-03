package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpfrra;
import br.com.smaconsulting.sped.editor.entity.Rdtpse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdtpseRepository extends JpaRepository<Rdtpse, String> {
}
