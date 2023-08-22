package br.com.saart.service;

import br.com.saart.entity.Dirf;
import br.com.saart.repository.DirfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DirfService {

    @Autowired
    private DirfRepository dirfRepository;

    public Dirf save(Dirf dirf) {
        return dirfRepository.save(dirf);
    }

    public void delete(Long id) {
        dirfRepository.deleteById(id);
    }
}
