package com.educoin.web.service;

import com.educoin.web.model.Aluno;
import com.educoin.web.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorId(String rg) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(rg);
        return alunoOpt.orElse(null);
    }

    public void excluirPorId(String rg) {
        alunoRepository.deleteById(rg);
    }

    public boolean autenticar(String rg, String senha) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(rg);
        return alunoOpt.isPresent() && alunoOpt.get().getSenha().equals(senha);
    }

    public Aluno buscarPorRg(String rg) {
        return alunoRepository.findById(rg).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void excluirAluno(String rg) {
        alunoRepository.deleteById(rg);
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void adicionarMoedas(String rg, int quantidade) {
    Aluno aluno = alunoRepository.findById(rg).orElse(null);
    if (aluno != null) {
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() + quantidade);
        alunoRepository.save(aluno);
      }
    }
}
