package com.alkemy.challenge.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "pelicula")
@RestResource(rel = "movies",path = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idMovie;
    
    @Column(name = "titulo")
    @NotBlank
    private String titulo;

    @Column(name = "fecha")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "calificacion")
    @NotNull
    private int calificacion;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnoreProperties("movies")
    private Set<Character> characters = new HashSet<>();
    
    public Movie(){}

    public Movie(String titulo, Date fecha, int calificacion) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.calificacion = calificacion;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() { 
        return "titulo: " + titulo + " fecha de creacion: " + fecha + " calificacion: " + calificacion;
    }

}
