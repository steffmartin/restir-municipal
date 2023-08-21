package br.com.saart.repository;

import br.com.saart.entity.Dirf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirfRepository extends PagingAndSortingRepository<Dirf, Long>, CrudRepository<Dirf, Long> {
}
