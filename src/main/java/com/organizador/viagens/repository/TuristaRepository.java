package com.organizador.viagens.repository;
import com.organizador.viagens.model.Turista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuristaRepository extends JpaRepository<Turista, Long> {
    Turista findByNome(String nome);
}
