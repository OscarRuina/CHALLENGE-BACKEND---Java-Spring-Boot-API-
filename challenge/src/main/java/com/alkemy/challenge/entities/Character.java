package com.alkemy.challenge.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "personaje")
@RestResource(rel = "characters",path = "character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idCharacter;

    @Column(name = "nombre")
    @NotBlank
    private String nombre;

    @Column(name = "edad")
    @NotNull
    private int edad;

    @Column(name = "peso")
    @NotNull
    private float peso;

    @Column(name = "historia")
    @NotBlank
    @Size(max = 100)
    private String historia;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "personajes_peliculas",
        joinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("characters")
    private Set<Movie> movies = new HashSet<>();

    public Character(){}

    public Character(String nombre, int edad, float peso, String historia) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

    public long getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(long idCharacter) {
        this.idCharacter = idCharacter;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + " edad: " + edad + " peso: " + peso + " historia: " + historia + "peliculas: ";
    }

}
