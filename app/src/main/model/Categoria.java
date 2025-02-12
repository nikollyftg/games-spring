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
}