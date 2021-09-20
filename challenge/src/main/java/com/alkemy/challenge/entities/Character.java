package com.alkemy.challenge.entities;

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

@Entity
@Table(name = "personaje")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idCharacter;

    //private byte[] imagen;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @Column(name = "peso")
    private float peso;

    @Column(name = "historia")
    private String historia;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "personajes_peliculas",
        joinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id"))
    private Set<Movie> movies;

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
        return "Character [historia=" + historia + ", idCharacter=" + idCharacter + ", nombre=" + nombre + ", peso="
                + peso + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + edad;
        result = prime * result + ((movies == null) ? 0 : movies.hashCode());
        result = prime * result + ((historia == null) ? 0 : historia.hashCode());
        result = prime * result + (int) (idCharacter ^ (idCharacter >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        long temp;
        temp = Double.doubleToLongBits(peso);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Character other = (Character) obj;
        if (edad != other.edad)
            return false;
        if (movies == null) {
            if (other.movies != null)
                return false;
        } else if (!movies.equals(other.movies))
            return false;
        if (historia == null) {
            if (other.historia != null)
                return false;
        } else if (!historia.equals(other.historia))
            return false;
        if (idCharacter != other.idCharacter)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
            return false;
        return true;
    }

}
