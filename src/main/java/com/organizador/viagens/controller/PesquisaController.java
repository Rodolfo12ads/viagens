package com.organizador.viagens.controller;

import com.organizador.viagens.model.Acomodacao;
import com.organizador.viagens.model.Turista;
import com.organizador.viagens.model.Viagem;
import com.organizador.viagens.repository.AcomodacaoRepository;
import com.organizador.viagens.repository.TuristaRepository;
import com.organizador.viagens.repository.ViagemRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;
import java.util.List;

@Controller
public class PesquisaController {

    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private TuristaRepository turistaRepository;
    @Autowired
    private AcomodacaoRepository acomodacaoRepository;

    @GetMapping("/pesquisas" )
    public String showPesquisaPage(Model model, HttpServletRequest request) {
        model.addAttribute("requestURI", request.getRequestURI());
        // Inicializa as listas de resultados como vazias para não dar erro no template
        model.addAttribute("viagensResultado", Collections.emptyList());
        model.addAttribute("turistasResultado", Collections.emptyList());
        model.addAttribute("acomodacoesResultado", Collections.emptyList());
        return "Pesquisa/pesquisas";
    }

    @PostMapping("/pesquisas/viagensPorPreco")
    public String pesquisarViagensPorPreco(@RequestParam Double valorMinimo, Model model, HttpServletRequest request) {
        List<Viagem> viagens = viagemRepository.findViagensAcimaDePreco(valorMinimo);
        model.addAttribute("viagensResultado", viagens);
        model.addAttribute("valorMinimoPesquisado", valorMinimo); // Para mostrar o que foi pesquisado

        // Mantém os outros resultados vazios e adiciona a URI
        model.addAttribute("turistasResultado", Collections.emptyList());
        model.addAttribute("acomodacoesResultado", Collections.emptyList());
        model.addAttribute("requestURI", request.getRequestURI());
        return "Pesquisa/pesquisas";
    }

    @PostMapping("/pesquisas/turistaPorNome")
    public String pesquisarTuristaPorNome(@RequestParam String nome, Model model, HttpServletRequest request) {
        Turista turista = turistaRepository.findByNome(nome);

        model.addAttribute("turistasResultado", turista != null ? List.of(turista) : Collections.emptyList());
        model.addAttribute("nomePesquisado", nome);
        model.addAttribute("viagensResultado", Collections.emptyList());
        model.addAttribute("acomodacoesResultado", Collections.emptyList());
        model.addAttribute("requestURI", request.getRequestURI());
        return "Pesquisa/pesquisas";
    }

    @GetMapping("/pesquisas/acomodacoesNaoUtilizadas")
    public String pesquisarAcomodacoesNaoUtilizadas(Model model, HttpServletRequest request) {
        List<Acomodacao> acomodacoes = acomodacaoRepository.findAcomodacoesNaoUtilizadas();
        model.addAttribute("acomodacoesResultado", acomodacoes);
        model.addAttribute("pesquisaAcomodacaoFeita", true);
        model.addAttribute("viagensResultado", Collections.emptyList());
        model.addAttribute("turistasResultado", Collections.emptyList());
        model.addAttribute("requestURI", request.getRequestURI());
        return "Pesquisa/pesquisas";
    }
}
