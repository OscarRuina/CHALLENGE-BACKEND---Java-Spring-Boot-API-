package com.alkemy.challenge.repositories;

import java.util.List;

import com.alkemy.challenge.entities.Character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterRepository extends JpaRepository<Character,Long>{

    public List<Character> findByNombre(String nombre);
    public List<Character> findByEdad(int edad);
    
}
