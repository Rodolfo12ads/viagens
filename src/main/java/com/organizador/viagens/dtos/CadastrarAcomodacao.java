package com.organizador.viagens.dtos;

public record CadastrarAcomodacao(
        String nome,
        String tipo,
        String cidade,
        String pais,
        Double precoPorNoite
) { }
