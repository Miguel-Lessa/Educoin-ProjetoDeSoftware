package com.educoin.web.controller;

import com.educoin.web.model.Aluno;
import com.educoin.web.model.Professor;
import com.educoin.web.repository.InstituicaoRepository;
import com.educoin.web.repository.ProfessorRepository;
import com.educoin.web.repository.VantagemRepository;
import com.educoin.web.service.AlunoService;
import com.educoin.web.service.VantagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        model.addAttribute("instituicoes", instituicaoRepository.findAll());
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


    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @GetMapping("/alunos/cadastro")
    public String mostrarFormularioCadastro(Model model) {
     model.addAttribute("aluno", new Aluno());
     model.addAttribute("instituicoes", instituicaoRepository.findAll());
     return "aluno-cadastro";
    }

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private com.educoin.web.repository.AlunoRepository alunoRepository;
    
    @PostMapping("/alunos/cadastro")
    public String cadastrarAluno(@ModelAttribute Aluno aluno, Model model) {
        Professor professor = professorRepository.findByCurso(aluno.getCurso());
        aluno.setProfessor(professor);
        alunoRepository.save(aluno);
        return "redirect:/aluno-login";
    }


@Autowired
private VantagemRepository vantagemRepository;

@GetMapping("/home")
public String homeAluno(Model model, @RequestParam("rg") String rg) {
    Aluno aluno = alunoService.buscarPorRg(rg);
    model.addAttribute("aluno", aluno);
    model.addAttribute("vantagens", vantagemRepository.findAll());
    return "aluno-home";
}

@Autowired
private VantagemService vantagemService;

@PostMapping("/resgatar-vantagem")
public String resgatarVantagem(@RequestParam Long vantagemId, @RequestParam String alunoRg, RedirectAttributes redirectAttributes) {
    boolean sucesso = vantagemService.resgatarVantagem(alunoRg, vantagemId);
    if (sucesso) {
        redirectAttributes.addFlashAttribute("mensagem", "Vantagem resgatada! O código foi enviado para seu email.");
    } else {
        redirectAttributes.addFlashAttribute("erro", "Saldo insuficiente ou erro ao resgatar.");
    }
    return "redirect:/alunos/home?rg=" + alunoRg;
}


}
