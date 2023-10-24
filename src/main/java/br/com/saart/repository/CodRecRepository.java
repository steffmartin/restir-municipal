package br.com.saart.repository;

import br.com.saart.entity.codRec.CodReceita;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodRecRepository extends JpaSpecificationExecutor<CodReceita>, CrudRepository<CodReceita, String> {
}
