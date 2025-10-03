package com.organizador.viagens.dtos;

import com.organizador.viagens.model.Turista;

public record AlterarViagem(Long id,
                            String pais,
                            Integer duracao,
                            Double preco,
                            Long idTurista,
                            Long idAcomodacao) {
}
