package com.organizador.viagens.model;
import com.organizador.viagens.dtos.AlterarViagem;
import com.organizador.viagens.dtos.CadastrarViagem;
import jakarta.persistence.*;

@Entity
@Table(name="Viagem")
public class Viagem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String pais;
    private Integer duracao;
    private Double preco;
    private String status;

    @ManyToOne
    @JoinColumn(name = "idTurista")
    private Turista turista;

    @ManyToOne
    @JoinColumn(name="idAcomodacao")
    private Acomodacao acomodacao;

    public Viagem(){};

    public Viagem(CadastrarViagem dados, Turista turista, Acomodacao acomodacao) {
        this.pais = dados.pais();
        this.duracao = dados.duracao();
        this.preco = dados.preco();
        this.turista = turista;
        this.acomodacao = acomodacao;
        this.status = "ATIVA";
    }


    public void atualizaViagem(AlterarViagem dados, Turista turista, Acomodacao acomodacao) {
        // Se o DTO não tiver o pais, remova esta linha
        if (dados.pais() != null) {
            this.pais = dados.pais();
        }
        // Se o DTO não tiver a duracao, remova esta linha
        if (dados.duracao() != null) {
            this.duracao = dados.duracao();
        }
        // Se o DTO não tiver o preco, remova esta linha
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }

        this.turista = turista;
        this.acomodacao = acomodacao;
    }


    // getters

    public Turista getTurista() {
        return turista;
    }

    public Acomodacao getAcomodacao() {return acomodacao;}

    public String getPais() {
        return pais;
    }
    public Long getId() {
        return id;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public double getPreco() {
        return preco;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
