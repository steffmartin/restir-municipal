package br.com.saart.repository;

import br.com.saart.entity.dirf.Declarante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclaranteRepository extends JpaSpecificationExecutor<Declarante>, CrudRepository<Declarante, Long> {

}
