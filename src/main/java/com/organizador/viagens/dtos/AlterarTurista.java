package com.organizador.viagens.dtos;

public record AlterarTurista(
        Long idTurista,
        String nome,
        Integer idade,
        String nacionalidade,
        String sexo
) { }
