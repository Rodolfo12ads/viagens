package com.organizador.viagens.controller;

import com.organizador.viagens.repository.TuristaRepository;
import com.organizador.viagens.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private ViagemRepository viagemRepository;

    @GetMapping("/dashboard" )
    public String showDashboard(Model model, HttpServletRequest request) {

        long totalTuristas = turistaRepository.count();
        long viagensAtivas = viagemRepository.count();
        double receitaMensal = 5200.00;
        long novasReservas = 23;

        model.addAttribute("totalTuristas", totalTuristas);
        model.addAttribute("viagensAtivas", viagensAtivas);
        model.addAttribute("receitaMensal", receitaMensal);
        model.addAttribute("novasReservas", novasReservas);

        model.addAttribute("requestURI", request.getRequestURI());

        return "dashboard";
    }
}
