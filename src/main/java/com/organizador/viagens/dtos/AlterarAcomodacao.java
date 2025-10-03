package com.organizador.viagens.dtos;

public record AlterarAcomodacao(
        Long idAcomodacao,
        String nome,
        String tipo,
        String cidade,
        Double precoPorNoite,
        String pais
) {
}
