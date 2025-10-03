package com.organizador.viagens.dtos;

import com.organizador.viagens.model.Turista;

public record CadastrarViagem(String pais,
                              Integer duracao,
                              Double preco,
                              Long idTurista,
                              Long idAcomodacao) {
}
