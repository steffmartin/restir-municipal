package br.com.saart.service;

import br.com.saart.entity.dirf.Dirf;
import br.com.saart.repository.DirfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirfService {

    @Autowired
    private DirfRepository dirfRepository;

    @Transactional
    public Dirf save(Dirf dirf) {
        return dirfRepository.save(dirf);
    }

    @Transactional
    public void delete(Long id) {
        dirfRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Dirf> findAll(Pageable page, Specification<Dirf> specification) {
        return dirfRepository.findAll(specification, page);
    }
}
