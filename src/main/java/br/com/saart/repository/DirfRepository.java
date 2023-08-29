package br.com.saart.repository;

import br.com.saart.entity.Dirf;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirfRepository extends JpaSpecificationExecutor<Dirf>, CrudRepository<Dirf, Long> {
}
