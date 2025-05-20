package com.educoin.web.service;

import com.educoin.web.model.Aluno;
import com.educoin.web.model.Vantagem;
import com.educoin.web.repository.VantagemRepository;
import com.educoin.web.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EmailService emailService;

    public List<Vantagem> listarTodas() {
        return vantagemRepository.findAll();
    }

    public void salvar(Vantagem vantagem) {
        vantagemRepository.save(vantagem);
    }

    public Vantagem buscarPorId(Long id) {
        return vantagemRepository.findById(id).orElse(null);
    }

    public void excluirPorId(Long id) {
        vantagemRepository.deleteById(id);
    }

    public boolean resgatarVantagem(String alunoRg, Long vantagemId) {
        Aluno aluno = alunoRepository.findById(alunoRg).orElse(null);
        Vantagem vantagem = vantagemRepository.findById(vantagemId).orElse(null);
        if (aluno != null && vantagem != null && aluno.getSaldoMoedas() >= vantagem.getCustoMoeda()) {
            aluno.setSaldoMoedas(aluno.getSaldoMoedas() - vantagem.getCustoMoeda());
            alunoRepository.save(aluno);
            // Envie o id da vantagem por email para o aluno
            emailService.enviar(
                aluno.getEmail(),
                "Resgate de Vantagem",
                "Você resgatou a vantagem: " + vantagem.getNome() + ". Código: " + vantagem.getId()
            );
            return true;
        }
        return false;
    }
}