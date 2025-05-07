package com.educoin.web.controller;

import com.educoin.web.model.Aluno;
import com.educoin.web.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "aluno-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{rg}")
    public String editar(@PathVariable String rg, Model model) {
        Aluno aluno = alunoService.buscarPorId(rg);
        if (aluno != null) {
            model.addAttribute("aluno", aluno);
            return "aluno-form";
        }
        return "redirect:/alunos";
    }

    @GetMapping("/excluir/{rg}")
    public String excluir(@PathVariable String rg) {
        alunoService.excluirPorId(rg);
        return "redirect:/alunos";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno-cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/alunos/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno-login";
    }

    @PostMapping("/login")
    public String loginAluno(@RequestParam String rg, @RequestParam String senha, Model model) {
        Aluno aluno = alunoService.buscarPorRg(rg);
        if (aluno != null && aluno.getSenha().equals(senha)) {
            return "redirect:/alunos/home?rg=" + aluno.getRg();
        } else {
            model.addAttribute("erro", "Credenciais inválidas");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homeAluno(Model model, @RequestParam("rg") String rg) {

        Aluno aluno = alunoService.buscarPorRg(rg);
        model.addAttribute("aluno", aluno);
        return "aluno-home";
    }

}
