package br.com.saart.service;

import br.com.saart.entity.codRec.CodReceita;
import br.com.saart.repository.CodRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CodRecService {

    @Autowired
    private CodRecRepository codRecRepository;

    @Transactional
    public CodReceita save(CodReceita codReceita) {
        return codRecRepository.save(codReceita);
    }

    @Transactional
    public void delete(String id) {
        codRecRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<CodReceita> findAll(Pageable page, Specification<CodReceita> specification) {
        return codRecRepository.findAll(specification, page);
    }
}
