package com.alkemy.challenge.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.alkemy.challenge.entities.Character;
import com.alkemy.challenge.services.CharacterServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterServiceImp service;

    @GetMapping("/characters")
    public List<Character> list(){
        return service.listAll();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> get(@PathVariable long id){
        try{
            Character character = service.get(id);
            return new ResponseEntity<Character>(character,HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<Character>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/characters")
    public void add(@RequestBody Character character){
        service.save(character);
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<?> update(@RequestBody Character character,@PathVariable long id){
        try{
            Character existCharacter = service.get(id);
            existCharacter.setNombre(character.getNombre());
            existCharacter.setPeso(character.getPeso());
            existCharacter.setEdad(character.getEdad());
            existCharacter.setHistoria(character.getHistoria());
            existCharacter.setMovies(character.getMovies());
            service.save(existCharacter);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/characters/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }

}
