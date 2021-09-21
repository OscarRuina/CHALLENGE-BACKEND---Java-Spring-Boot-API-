package com.alkemy.challenge.model;

public class CharacterModel {

    //private byte[] imagen;
    private String nombre;
    
    public CharacterModel(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CharacterModel [nombre=" + nombre + "]";
    }    
    
}
