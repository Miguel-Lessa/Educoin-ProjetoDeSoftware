package com.educoin.web.controller;

import com.educoin.web.model.Vantagem;
import com.educoin.web.model.EmpresaParceira;
import com.educoin.web.service.VantagemService;
import com.educoin.web.repository.EmpresaParceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vantagens")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

    @Autowired
    private EmpresaParceiraRepository empresaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vantagens", vantagemService.listarTodas());
        return "vantagem-list";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("vantagem", new Vantagem());
        model.addAttribute("empresas", empresaRepository.findAll());
        return "empresa-home";
    }

    @PostMapping
public String salvar(@ModelAttribute Vantagem vantagem, Model model) {
    vantagemService.salvar(vantagem);
    EmpresaParceira empresa = vantagem.getEmpresaParceira();
    model.addAttribute("empresa", empresa);
    model.addAttribute("vantagem", new Vantagem());
    model.addAttribute("mensagem", "Vantagem cadastrada com sucesso!");
    return "empresa-home";
}

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("vantagem", vantagemService.buscarPorId(id));
        model.addAttribute("empresas", empresaRepository.findAll());
        return "vantagem-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        vantagemService.excluirPorId(id);
        return "redirect:/vantagens";
    }


    
}

