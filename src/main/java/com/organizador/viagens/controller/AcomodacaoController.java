package com.organizador.viagens.controller;

import com.organizador.viagens.dtos.AlterarAcomodacao;
import com.organizador.viagens.dtos.CadastrarAcomodacao;
import com.organizador.viagens.model.Acomodacao;
import com.organizador.viagens.repository.AcomodacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/acomodacoes")
public class AcomodacaoController {

    @Autowired
    private AcomodacaoRepository acomodacaoRepository;

    @GetMapping
    public String listarAcomodacoes(Model model) {
        List<Acomodacao> acomodacoes = acomodacaoRepository.findAll();
        model.addAttribute("acomodacoes", acomodacoes);
        return "acomodacoes/listar";
    }

    @GetMapping("/novo")
    public String formularioNovaAcomodacao(Model model) {
        model.addAttribute("acomodacao", new CadastrarAcomodacao(
                "", "", "", "", 0.0
        ));
        return "acomodacoes/formulario";
    }


    @PostMapping
    public String cadastrarAcomodacao(@ModelAttribute CadastrarAcomodacao dados) {
        Acomodacao acomodacao = new Acomodacao(dados);
        acomodacaoRepository.save(acomodacao);
        return "redirect:/acomodacoes";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditarAcomodacao(@PathVariable Long id, Model model) {
        Acomodacao acomodacao = acomodacaoRepository.findById(id).orElseThrow();
        model.addAttribute("acomodacao", new AlterarAcomodacao(
                acomodacao.getIdAcomodacao(),
                acomodacao.getNome(),
                acomodacao.getTipo(),
                acomodacao.getCidade(),
                acomodacao.getPrecoPorNoite(),
                acomodacao.getPais()
        ));
        return "acomodacoes/editar";
    }

    @PutMapping("/{id}")
    public String atualizarAcomodacao(@PathVariable Long id, @ModelAttribute AlterarAcomodacao dados) {
        Acomodacao acomodacao = acomodacaoRepository.findById(id).orElseThrow();
        acomodacao.atualizaAcomodacao(dados);
        acomodacaoRepository.save(acomodacao);
        return "redirect:/acomodacoes";
    }

    @DeleteMapping("/{id}")
    public String excluirAcomodacao(@PathVariable Long id) {
        acomodacaoRepository.deleteById(id);
        return "redirect:/acomodacoes";
    }
}