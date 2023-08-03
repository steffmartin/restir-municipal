package br.com.smaconsulting.sped.editor.repository;

import br.com.smaconsulting.sped.editor.entity.RegValAnoSemRet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegValAnoSemRetRepository extends JpaRepository<RegValAnoSemRet, String> {
}