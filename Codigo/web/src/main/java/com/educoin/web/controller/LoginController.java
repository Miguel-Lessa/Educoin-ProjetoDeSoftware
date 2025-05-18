package com.educoin.web.controller;

import com.educoin.web.model.Aluno;
import com.educoin.web.model.EmpresaParceira;
import com.educoin.web.model.Professor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/aluno-login")
    public String alunoLogin(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno-login";
    }

    @GetMapping("/empresa-login")
    public String empresaLogin(Model model) {
        model.addAttribute("empresa", new EmpresaParceira());
        return "empresa-login";
    }

    @GetMapping("/professor-login")
    public String professorLogin(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor-login";
    }
}