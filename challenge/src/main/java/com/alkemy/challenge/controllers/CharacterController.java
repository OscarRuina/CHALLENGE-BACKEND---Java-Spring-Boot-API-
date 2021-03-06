package com.alkemy.challenge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.alkemy.challenge.entities.Character;
import com.alkemy.challenge.entities.Movie;
import com.alkemy.challenge.model.CharacterModel;
import com.alkemy.challenge.services.CharacterServiceImp;
import com.alkemy.challenge.services.MovieServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterServiceImp service;

    @Autowired
    private MovieServiceImp movieService;

    @GetMapping("/characters")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<CharacterModel> list(){
        //puede retornar lista vacia si no hay nada cargado
        List<CharacterModel> models = new ArrayList<CharacterModel>();
        for(Character c : service.listAll()){
            models.add(new CharacterModel(c.getNombre()));
        }
        return models;
    }

    @GetMapping("/characters/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Character> get(@PathVariable long id){
        try{
            Character character = service.get(id);
            return new ResponseEntity<Character>(character,HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<Character>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/characters")
    @PreAuthorize("hasRole('ADMIN')")
    public void add(@RequestBody Character character){
        service.save(character);
    }

    @PutMapping("/characters/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @PutMapping("/characters/{id}/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovies(@RequestBody Movie movie,@PathVariable long id){
        try{
            Character existCharacter = service.get(id);
            Movie existMovie = movieService.get(movie.getIdMovie());
            existCharacter.getMovies().add(existMovie);
            service.save(existCharacter);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("GET/characters/name")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Character>> getByName(@RequestParam(name = "name") String nombre){
        try{
            List<Character> characters = service.getByNombre(nombre);
            return new ResponseEntity<List<Character>>(characters,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<List<Character>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("GET/characters/age")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Character>> getByAge(@RequestParam(name = "age") int edad){
        try{
            List<Character> characters = service.getByAge(edad);
            return new ResponseEntity<List<Character>>(characters,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<List<Character>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("GET/characters/movies")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Set<Character>> getByIdMovie(@RequestParam(name = "movies") long idMovie){
        try{
            Movie movie = movieService.get(idMovie);
            Set<Character> characters = movie.getCharacters();
            return new ResponseEntity<Set<Character>>(characters,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Set<Character>>(HttpStatus.NOT_FOUND);
        }
    }

}
