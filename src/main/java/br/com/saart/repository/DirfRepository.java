package br.com.saart.repository;

import br.com.saart.entity.Dirf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirfRepository extends JpaRepository<Dirf, Long> {
}
