package com.alkemy.challenge.model;

import java.time.LocalDate;

public class MovieModel {

    //private byte[] imagen;
    private String titulo;
    private LocalDate fecha;
    
    public MovieModel(String titulo, LocalDate fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "MovieModel [fecha=" + fecha + ", titulo=" + titulo + "]";
    }

}
