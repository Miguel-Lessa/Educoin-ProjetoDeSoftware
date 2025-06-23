package com.educoin.web.controller;

import com.educoin.web.model.EmpresaParceira;
import com.educoin.web.model.Vantagem;
import com.educoin.web.service.EmpresaParceiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas")
public class EmpresaParceiraController {

    @Autowired
    private EmpresaParceiraService service;  

    

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empresas", service.listarTodas());
        return "empresa-list";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("empresa", new EmpresaParceira());
        return "empresa-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute EmpresaParceira empresa) {
        service.salvar(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/editar/{cnpj}")
    public String editar(@PathVariable String cnpj, Model model) {
        model.addAttribute("empresa", service.buscarPorCnpj(cnpj));
        return "empresa-form";
    }

    @GetMapping("/excluir/{cnpj}")
    public String excluir(@PathVariable String cnpj) {
        service.excluirPorCnpj(cnpj);
        return "redirect:/empresas";
    }

    @GetMapping("/empresa/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("empresa", new EmpresaParceira());
        return "empresa-cadastro";
    }

    @PostMapping("/empresa/cadastrar")
    public String cadastrarEmpresa(@ModelAttribute EmpresaParceira empresa) {
        service.salvar(empresa);
        return "redirect:/empresas/empresa/login";
    }

    @GetMapping("/empresa/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("empresa", new EmpresaParceira());
        return "empresa-login";
    }

    @PostMapping("/empresa/login")
    public String login(@ModelAttribute EmpresaParceira empresa, Model model) {
    EmpresaParceira existente = service.buscarPorCnpj(empresa.getCnpj());
    if (existente != null && existente.getSenha().equals(empresa.getSenha())) {
        model.addAttribute("empresa", existente);
        Vantagem vantagem = new Vantagem();
        vantagem.setEmpresaParceira(existente);
        model.addAttribute("vantagem", vantagem);
        return "empresa-home";
    } else {
        model.addAttribute("erro", "CNPJ ou senha inválidos");
        return "empresa-login";
    }
}

@GetMapping("/home")
public String homeEmpresa(Model model, @RequestParam("cnpj") String cnpj) {
    EmpresaParceira empresa = service.buscarPorCnpj(cnpj);
    Vantagem vantagem = new Vantagem();
    vantagem.setEmpresaParceira(empresa); // já associa a empresa
    model.addAttribute("empresa", empresa);
    model.addAttribute("vantagem", vantagem);
    return "empresa-home";
}
}
