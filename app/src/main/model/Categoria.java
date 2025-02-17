package application.model;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Enity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GenerationValue;
import jakarta.persistence.OneToMany;



@Enity
@Table (name = "categorias")

public class Categoria{
    @Id 
    @GeneratedValue (strategy =GenerationType.IDENITY)
    private long id;
    @Column (unique=true. nullabel-false)

    private String name;

    @OneTomany(mappedBy = "Categoria")
    private Set<Jogo> jogos = new HashSet<>();

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Set<Jogo> getJogos(){
        return jogos;
    }

    public void setJogos(Set<jogo> jogos){
        this.jogos = jogos;
    } 
}