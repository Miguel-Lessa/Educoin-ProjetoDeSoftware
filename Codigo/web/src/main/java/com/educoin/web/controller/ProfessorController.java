package com.educoin.web.controller;

import com.educoin.web.model.Professor;
import com.educoin.web.service.ProfessorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.educoin.web.repository.AlunoRepository;
import com.educoin.web.repository.InstituicaoRepository;
import com.educoin.web.model.Aluno;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("professores", professorService.listarTodos());
        return "professor-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Professor professor) {
        professorService.salvar(professor);
        return "redirect:/professores";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Professor professor) {
        professor.setSaldoMoedas(1000);
        professorService.salvar(professor);
        return "redirect:/professores/login";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Professor professor = professorService.buscarPorId(id);
        if (professor != null) {
            model.addAttribute("professor", professor);
            return "professor-form";
        }
        return "redirect:/professores";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable String id) {
        professorService.excluirPorId(id);
        return "redirect:/professores";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor-login";
    }

    @PostMapping("/login")
    public String loginProfessor(@RequestParam String id, @RequestParam String senha, Model model) {
        if (professorService.autenticar(id, senha)) {
            return "redirect:/professores/home?id=" + id;
        } else {
            model.addAttribute("erro", "Credenciais inválidas");
            return "professor-login";
        }
    }

    @GetMapping("/home")
    public String homeProfessor(Model model, @RequestParam("id") String id) {
        Professor professor = professorService.buscarPorId(id);
        model.addAttribute("professor", professor);
        List<Aluno> alunos = alunoRepository.findByCurso(professor.getCurso());
        model.addAttribute("alunos", alunos);
        return "professor-home";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
     model.addAttribute("professor", new Professor());
     model.addAttribute("instituicoes", instituicaoRepository.findAll());
    return "professor-cadastro";
    }


    @Autowired
    private com.educoin.web.service.AlunoService alunoService;
    @PostMapping("/enviar-moedas")
    public String enviarMoedas(@RequestParam String alunoRg, @RequestParam int quantidade, @RequestParam String professorId, Model model) {
        Professor professor = professorService.buscarPorId(professorId);
        
             if (professor.getSaldoMoedas() >= quantidade) {
                alunoService.adicionarMoedas(alunoRg, quantidade);
                professor.setSaldoMoedas(professor.getSaldoMoedas() - quantidade);
                professorService.salvar(professor);
            } else {
                model.addAttribute("erro", "Saldo insuficiente para enviar moedas.");
            }
         return "redirect:/professores/home?id=" + professorId;
        }
    

}