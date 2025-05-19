package com.educoin.web.service;


import com.educoin.web.model.Aluno;
import com.educoin.web.model.Vantagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.educoin.web.repository.VantagemRepository;
import com.educoin.web.repository.AlunoRepository;
import java.util.List;

@Service
public class VantagemService {
    @Autowired
    private VantagemRepository repository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EmailService emailService;
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

    public boolean resgatarVantagem(String alunoRg, Long vantagemId) {
    Aluno aluno = alunoRepository.findById(alunoRg).orElse(null);
    Vantagem vantagem = repository.findById(vantagemId).orElse(null);
    if (aluno != null && vantagem != null && aluno.getSaldoMoedas() >= vantagem.getCustoMoeda()) {
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() - vantagem.getCustoMoeda());
        alunoRepository.save(aluno);
        // Envie o id da vantagem por email para o aluno
        emailService.enviar(aluno.getEmail(), "Resgate de Vantagem", "Você resgatou a vantagem: " + vantagem.getNome() + ". Código: " + vantagem.getId());
        return true;
    }
    return false;
}
}
