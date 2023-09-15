package br.com.saart.service;

import br.com.saart.entity.selic.Selic;
import br.com.saart.repository.SelicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SelicService {

    @Autowired
    private SelicRepository selicRepository;

    @Transactional
    public Selic save(Selic selic) {
        return selicRepository.save(selic);
    }

    @Transactional
    public void delete(Long id) {
        selicRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Selic> findAll(Pageable page, Specification<Selic> specification) {
        return selicRepository.findAll(specification, page);
    }
}
