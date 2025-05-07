package com.educoin.web.service;

import com.educoin.web.model.EmpresaParceira;
import com.educoin.web.repository.EmpresaParceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaParceiraService {

    @Autowired
    private EmpresaParceiraRepository repository;

    public List<EmpresaParceira> listarTodas() {
        return repository.findAll();
    }

    public void salvar(EmpresaParceira empresa) {
        repository.save(empresa);
    }

    public EmpresaParceira buscarPorCnpj(String cnpj) {
        return repository.findById(cnpj).orElse(null);
    }

    public void excluirPorCnpj(String cnpj) {
        repository.deleteById(cnpj);
    }
}
