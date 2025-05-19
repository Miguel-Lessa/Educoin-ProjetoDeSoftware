package com.educoin.web.repository;

import com.educoin.web.model.Aluno;
import com.educoin.web.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Aluno findByRg(String rg);
    List<Aluno> findByProfessor(Professor professor);
    List<Aluno> findByCurso(String curso);
}
