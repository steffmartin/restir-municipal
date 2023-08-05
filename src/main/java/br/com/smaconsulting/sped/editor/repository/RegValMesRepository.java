package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.RegValMes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegValMesRepository extends JpaRepository<RegValMes, Integer> {
}
