package com.organizador.viagens.controller;

import com.organizador.viagens.dtos.AlterarViagem;
import com.organizador.viagens.dtos.CadastrarViagem;
import com.organizador.viagens.model.Viagem;
import com.organizador.viagens.model.Turista;
import com.organizador.viagens.model.Acomodacao;
import com.organizador.viagens.repository.ViagemRepository;
import com.organizador.viagens.repository.TuristaRepository;
import com.organizador.viagens.repository.AcomodacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private AcomodacaoRepository acomodacaoRepository;

    @GetMapping
    public String listarViagens(Model model) {
        List<Viagem> viagens = viagemRepository.findAll();
        model.addAttribute("viagens", viagens);
        return "viagens/listar";
    }

    @GetMapping("/novo")
    public String formularioNovaViagem(Model model) {
        List<Turista> turistas = turistaRepository.findAll();
        List<Acomodacao> acomodacoes = acomodacaoRepository.findAll();

        model.addAttribute("viagem", new CadastrarViagem("", 0, 0.0, null, null));
        model.addAttribute("turistas", turistas);
        model.addAttribute("acomodacoes", acomodacoes);
        return "viagens/formulario";
    }

    @PostMapping
    public String cadastrarViagem(@ModelAttribute CadastrarViagem dados) {
        Turista turista = turistaRepository.findById(dados.idTurista()).orElseThrow();
        Acomodacao acomodacao = acomodacaoRepository.findById(dados.idAcomodacao()).orElseThrow();

        Viagem viagem = new Viagem(dados, turista, acomodacao);
        viagemRepository.save(viagem);
        return "redirect:/viagens";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditarViagem(@PathVariable Long id, Model model) {
        Viagem viagem = viagemRepository.findById(id).orElseThrow();
        List<Turista> turistas = turistaRepository.findAll();
        List<Acomodacao> acomodacoes = acomodacaoRepository.findAll();

        model.addAttribute("viagem", new AlterarViagem(
                viagem.getId(),
                viagem.getPais(),
                viagem.getDuracao(),
                viagem.getPreco(),
                viagem.getTurista().getIdTurista(),
                viagem.getAcomodacao().getIdAcomodacao()
        ));
        model.addAttribute("turistas", turistas);
        model.addAttribute("acomodacoes", acomodacoes);
        return "viagens/editar";
    }

    @PutMapping("/{id}")
    public String atualizarViagem(@PathVariable Long id, @ModelAttribute AlterarViagem dados) {
        Viagem viagem = viagemRepository.findById(id).orElseThrow();
        Turista turista = turistaRepository.findById(dados.idTurista()).orElseThrow();
        Acomodacao acomodacao = acomodacaoRepository.findById(dados.idAcomodacao()).orElseThrow();

        viagem.atualizaViagem(dados, turista, acomodacao);
        viagemRepository.save(viagem);
        return "redirect:/viagens";
    }

    @DeleteMapping("/{id}")
    public String excluirViagem(@PathVariable Long id) {
        viagemRepository.deleteById(id);
        return "redirect:/viagens";
    }



    @GetMapping("/pesquisas")
    @ResponseBody
    public String testarConsultas() {
        Turista turista = turistaRepository.findByNome("João da Silva"); // Use um nome que exista no seu banco
        System.out.println("Turista encontrado: " + (turista != null ? turista.getNome() : "Nenhum"));

        List<Acomodacao> hoteisEmParis = acomodacaoRepository.findByCidadeAndTipo("Paris", "Hotel");
        System.out.println("Hotéis em Paris: " + hoteisEmParis.size());

        List<Acomodacao> acomodacoesSemUso = acomodacaoRepository.findAcomodacoesNaoUtilizadas();
        System.out.println("Acomodações sem uso: " + acomodacoesSemUso.size());

        // Testando JPQL
        List<Viagem> viagensParaOJapao = viagemRepository.findViagensPorPaisOrdenadoPorDuracao("Japão");
        System.out.println("Viagens para o Japão: " + viagensParaOJapao.size());

        List<Viagem> viagensCaras = viagemRepository.findViagensAcimaDePreco(5000.00);
        System.out.println("Viagens acima de R$5000: " + viagensCaras.size());

        return "Consultas executadas! Verifique o console da sua aplicação.";
    }

}