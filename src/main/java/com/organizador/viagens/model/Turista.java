package com.organizador.viagens.model;
import com.organizador.viagens.dtos.AlterarTurista;
import com.organizador.viagens.dtos.CadastrarTurista;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Turista")
public class Turista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurista;
    private String nome;
    private Integer idade;
    private String nacionalidade;
    private String sexo;

    @OneToMany(mappedBy = "turista")
    private List<Viagem> viagens = new ArrayList<>();

    public Turista() {}

    public Turista(AlterarTurista dados){}

    public Turista(CadastrarTurista dados){
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.nacionalidade = dados.nacionalidade();
        this.sexo = dados.sexo();
    }

    public void atualizaTurista (AlterarTurista dados){
        this.idTurista = dados.idTurista();
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.nacionalidade = dados.nacionalidade();
        this.sexo = dados.sexo();
    }

    public Long getIdTurista() { return idTurista; }
    public String getNome() { return nome; }
    public Integer getIdade() { return idade; }
    public String getNacionalidade() { return nacionalidade; }
    public String getSexo() { return sexo; }
    public List<Viagem> getViagens() { return viagens; }
}
