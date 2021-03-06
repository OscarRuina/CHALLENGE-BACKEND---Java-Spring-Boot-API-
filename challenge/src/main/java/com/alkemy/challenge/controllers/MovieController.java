package com.alkemy.challenge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.alkemy.challenge.entities.Character;
import com.alkemy.challenge.entities.Genre;
import com.alkemy.challenge.entities.Movie;
import com.alkemy.challenge.model.MovieModel;
import com.alkemy.challenge.services.CharacterServiceImp;
import com.alkemy.challenge.services.GenreServiceImp;
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
public class MovieController {
    
    @Autowired
    private MovieServiceImp service;

    @Autowired
    private GenreServiceImp genreService;

    @Autowired
    private CharacterServiceImp characterService; 

    @GetMapping("GET/movies")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<MovieModel> list(){
        //puede retornar lista vacia si no hay nada cargado
        List<MovieModel> models = new ArrayList<MovieModel>();
        for(Movie c : service.listAll()){
            models.add(new MovieModel(c.getTitulo(),c.getFecha()));
        }
        return models;
    }

    @GetMapping("/movies/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Movie> get(@PathVariable long id){
        try{
            Movie movie = service.get(id);
            return new ResponseEntity<Movie>(movie,HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public void add(@RequestBody Movie movie){
        service.save(movie);
    }

    @PutMapping("/movies/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Movie movie,@PathVariable long id){
        try{
            Movie existMovie = service.get(id);
            existMovie.setTitulo(movie.getTitulo());
            existMovie.setFecha(movie.getFecha());
            existMovie.setCalificacion(movie.getCalificacion());
            existMovie.setCharacters(movie.getCharacters());
            service.save(existMovie);
            return new ResponseEntity<>(HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movies/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
    
    @PutMapping("/movies/{id}/characters")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCharacters(@RequestBody Character character,@PathVariable long id){
        try{
            Movie existMovie = service.get(id);
            Character exisCharacter = characterService.get(character.getIdCharacter());
            existMovie.getCharacters().add(exisCharacter);
            service.save(existMovie);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movies/name")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Movie>> getByName(@RequestParam(name = "name") String titulo){
        try{
            List<Movie> movies = service.getByName(titulo);
            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movies/genre")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Set<Movie>> getByGenre(@RequestParam(name = "genre") long idGenre){
        try{
            Genre g = genreService.get(idGenre);
            Set<Movie> movies = g.getMovies();
            return new ResponseEntity<Set<Movie>>(movies,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Set<Movie>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movies/order")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Movie>> getByOrder(@RequestParam(name = "order") String order){
        try{
            List<Movie> movies = service.getByOrder(order);
            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
        }
    }
}
