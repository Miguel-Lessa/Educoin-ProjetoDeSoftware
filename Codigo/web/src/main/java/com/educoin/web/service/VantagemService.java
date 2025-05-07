package com.educoin.web.service;


import com.educoin.web.model.Vantagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.educoin.web.repository.VantagemRepository;

import java.util.List;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository repository;

    public List<Vantagem> listarTodas() {
        return repository.findAll();
    }

    public void salvar(Vantagem vantagem) {
        repository.save(vantagem);
    }

    public Vantagem buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }
}
