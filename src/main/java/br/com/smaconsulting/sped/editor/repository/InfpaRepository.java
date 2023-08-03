package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Bpffci;
import br.com.smaconsulting.sped.editor.entity.Infpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfpaRepository extends JpaRepository<Infpa, String> {
}