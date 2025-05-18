package com.educoin.web.controller;

import com.educoin.web.model.Professor;
import com.educoin.web.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

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
        return "professor-home";
    }



}