package com.organizador.viagens.controller;

import com.organizador.viagens.dtos.AlterarTurista;
import com.organizador.viagens.dtos.CadastrarTurista;
import com.organizador.viagens.model.Turista;
import com.organizador.viagens.repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import java.util.List;

@Controller
@RequestMapping("/turistas")
public class TuristaController {

    @Autowired
    private TuristaRepository turistaRepository;

    @Configuration
    public class WebConfig {
        @Bean
        public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
            return new HiddenHttpMethodFilter();
        }
    }

    @GetMapping
    public String listarTuristas(Model model) {
        List<Turista> turistas = turistaRepository.findAll();
        model.addAttribute("turistas", turistas);
        return "turistas/listar";
    }

    @GetMapping("/novo")
    public String formularioNovoTurista(Model model) {
        model.addAttribute("turista", new CadastrarTurista("", 0, "", ""));
        return "turistas/formulario";
    }

    @PostMapping
    public String cadastrarTurista(@ModelAttribute CadastrarTurista dados) {
        Turista turista = new Turista(dados);
        turistaRepository.save(turista);
        return "redirect:/turistas";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditarTurista(@PathVariable Long id, Model model) {
        Turista turista = turistaRepository.findById(id).orElseThrow();
        model.addAttribute("turista", new AlterarTurista(
                turista.getIdTurista(),
                turista.getNome(),
                turista.getIdade(),
                turista.getNacionalidade(),
                turista.getSexo()
        ));
        return "turistas/editar";
    }

    @PutMapping("/{id}")
    public String atualizarTurista(@PathVariable Long id, @ModelAttribute AlterarTurista dados) {
        Turista turista = turistaRepository.findById(id).orElseThrow();
        turista.atualizaTurista(dados);
        turistaRepository.save(turista);
        return "redirect:/turistas";
    }

    @DeleteMapping("/{id}")
    public String excluirTurista(@PathVariable Long id) {
        turistaRepository.deleteById(id);
        return "redirect:/turistas";
    }
}