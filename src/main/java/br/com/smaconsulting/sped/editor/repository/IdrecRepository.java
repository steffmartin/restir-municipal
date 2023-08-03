package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.Idrec;
import br.com.smaconsulting.sped.editor.entity.Respo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdrecRepository extends JpaRepository<Idrec, String> {
}
