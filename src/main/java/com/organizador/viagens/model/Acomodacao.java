package com.organizador.viagens.model;
import com.organizador.viagens.dtos.AlterarAcomodacao;
import com.organizador.viagens.dtos.CadastrarAcomodacao;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Acomodacao")
public class Acomodacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcomodacao;
    private String nome;
    private String tipo;
    private String cidade;
    private String pais;
    private Double precoPorNoite;

    @OneToMany(mappedBy = "acomodacao")
    private java.util.List<Viagem> viagens = new java.util.ArrayList<>();

    public Acomodacao(){}

    public Acomodacao(CadastrarAcomodacao dados){
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.cidade = dados.cidade();
        this.pais = dados.pais();
        this.precoPorNoite = dados.precoPorNoite();
    }

    public void atualizaAcomodacao(AlterarAcomodacao dados){
        this.idAcomodacao = dados.idAcomodacao();
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.cidade = dados.cidade();
        this.pais = dados.pais();
        this.precoPorNoite = dados.precoPorNoite();
    }

    // getters
    public Long getIdAcomodacao() {
        return idAcomodacao;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPais() {return  pais;}

    public Double getPrecoPorNoite() {
        return precoPorNoite;
    }

    public java.util.List<Viagem> getViagens() {
        return viagens;
    }
}