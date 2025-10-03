package com.organizador.viagens.dtos;

public record CadastrarTurista(
        String nome,
        Integer idade,
        String nacionalidade,
        String sexo
) {}