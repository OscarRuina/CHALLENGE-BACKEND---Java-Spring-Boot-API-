package com.alkemy.challenge.model;

import java.sql.Date;

public class MovieModel {

    //private byte[] imagen;
    private String titulo;
    private Date fecha;
    
    public MovieModel(String titulo, Date fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "MovieModel [fecha=" + fecha + ", titulo=" + titulo + "]";
    }

}
