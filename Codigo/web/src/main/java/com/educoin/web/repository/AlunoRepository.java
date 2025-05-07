package com.educoin.web.repository;

import com.educoin.web.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Aluno findByRg(String rg);
}
