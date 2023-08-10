package br.com.smaconsulting.saart.repository;

import br.com.smaconsulting.saart.entity.Dirf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirfRepository extends JpaRepository<Dirf, Integer> {
}
