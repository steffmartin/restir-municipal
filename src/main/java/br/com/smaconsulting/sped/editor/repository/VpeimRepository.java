package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Vpeim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpeimRepository extends JpaRepository<Vpeim, Vpeim.VpeimId> {
}
