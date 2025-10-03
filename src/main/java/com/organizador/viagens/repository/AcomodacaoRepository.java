package com.organizador.viagens.repository;
import com.organizador.viagens.model.Acomodacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcomodacaoRepository extends JpaRepository<Acomodacao, Long>{
    List<Acomodacao> findByCidadeAndTipo(String cidade, String tipo);

    @Query(value = "SELECT a.* FROM Acomodacao a LEFT JOIN Viagem v ON a.id_acomodacao = v.id_acomodacao WHERE v.id IS NULL", nativeQuery = true)
    List<Acomodacao> findAcomodacoesNaoUtilizadas();
}
