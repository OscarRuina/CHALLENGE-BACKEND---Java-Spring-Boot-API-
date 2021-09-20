package com.alkemy.challenge.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idMovie;
    
    //private byte[] imagen;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha")
    private LocalDate fechaCreacion;

    @Column(name = "calificacion")
    private int calificacion;

    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters;
    
    public Movie(){}

    public Movie(String titulo, LocalDate fechaCreacion, int calificacion) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        return "Movie [calificacion=" + calificacion + ", fechaCreacion=" + fechaCreacion + ", titulo=" + titulo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(calificacion);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((characters == null) ? 0 : characters.hashCode());
        result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
        result = prime * result + (int) (idMovie ^ (idMovie >>> 32));
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (Double.doubleToLongBits(calificacion) != Double.doubleToLongBits(other.calificacion))
            return false;
        if (characters == null) {
            if (other.characters != null)
                return false;
        } else if (!characters.equals(other.characters))
            return false;
        if (fechaCreacion == null) {
            if (other.fechaCreacion != null)
                return false;
        } else if (!fechaCreacion.equals(other.fechaCreacion))
            return false;
        if (idMovie != other.idMovie)
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

}
