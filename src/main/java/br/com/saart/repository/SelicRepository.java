package br.com.saart.repository;

import br.com.saart.entity.selic.Selic;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelicRepository extends JpaSpecificationExecutor<Selic>, CrudRepository<Selic, Long> {
}
