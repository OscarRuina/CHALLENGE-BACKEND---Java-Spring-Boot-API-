package com.alkemy.challenge.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "genero")
@RestResource(rel = "genres",path = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idGenre;

    @Column(name = "nombre")
    @NotBlank
    private String nombre;

    @OneToMany
    private Set<Movie> movies = new HashSet<>(); 

    public Genre(){}

    public Genre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(long idGenre) {
        this.idGenre = idGenre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Genre [nombre=" + nombre + "]";
    }

}
