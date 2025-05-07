package com.educoin.web.repository;

import com.educoin.web.model.EmpresaParceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira, String> {
    EmpresaParceira findByCnpj(String cnpj);
}