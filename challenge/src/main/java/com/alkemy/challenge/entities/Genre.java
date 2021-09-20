package com.alkemy.challenge.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genero")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idGenre;

    @Column(name = "nombre")
    private String nombre;

    //private byte[] imagen;

    @OneToMany
    private Set<Movie> movies; 

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idGenre ^ (idGenre >>> 32));
        result = prime * result + ((movies == null) ? 0 : movies.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Genre other = (Genre) obj;
        if (idGenre != other.idGenre)
            return false;
        if (movies == null) {
            if (other.movies != null)
                return false;
        } else if (!movies.equals(other.movies))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }
    
}
