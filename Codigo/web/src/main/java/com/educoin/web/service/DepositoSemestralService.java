package com.educoin.web.service;

import com.educoin.web.model.Professor;
import com.educoin.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

public class DepositoSemestralService {

     @Autowired
    private ProfessorRepository professorRepository;
     @Scheduled(cron = "0 0 0 1 1,7 * ")
    public void adicionarBonusMensal() {
        List<Professor> professores = professorRepository.findAll();
        for (Professor professor : professores) {
            professor.setSaldoMoedas(professor.getSaldoMoedas() + 1000);
            professorRepository.save(professor);
        }
        System.out.println("Bônus mensal de 1000 moedas adicionado para todos os professores.");
    }

    
}
