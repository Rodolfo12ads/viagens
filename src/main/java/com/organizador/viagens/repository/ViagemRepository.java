package com.organizador.viagens.repository;

import com.organizador.viagens.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("SELECT v FROM Viagem v WHERE v.pais = :pais ORDER BY v.duracao DESC")
    List<Viagem> findViagensPorPaisOrdenadoPorDuracao(@Param("pais") String pais);

    @Query("SELECT v FROM Viagem v WHERE v.preco > :valorMinimo")
    List<Viagem> findViagensAcimaDePreco(@Param("valorMinimo") Double valor);


    // ==========================================================================
    //  ADICIONE O MÉTODO ABAIXO
    // ==========================================================================

    /**
     * Conta o número de viagens que possuem um determinado status.
     * Esta é uma consulta JPQL explícita que faz a mesma coisa que o método
     * derivado pelo nome 'countByStatus'.
     *
     * @param status O valor do status a ser procurado (ex: "ATIVA").
     * @return O número de viagens que correspondem ao status.
     */
    @Query("SELECT COUNT(v) FROM Viagem v WHERE v.status = :status")
    long countByStatus(@Param("status") String status);

}
