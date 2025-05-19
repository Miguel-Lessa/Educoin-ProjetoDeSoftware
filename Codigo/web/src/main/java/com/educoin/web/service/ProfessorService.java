package com.educoin.web.service;

import com.educoin.web.model.Professor;
import com.educoin.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {



    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor buscarPorId(String id) {
        Optional<Professor> professorOpt = professorRepository.findById(id);
        return professorOpt.orElse(null);
    }

    public void excluirPorId(String id) {
        professorRepository.deleteById(id);
    }

    public boolean autenticar(String id, String senha) {
        Optional<Professor> professorOpt = professorRepository.findById(id);
        return professorOpt.isPresent() && professorOpt.get().getSenha().equals(senha);
    }
}