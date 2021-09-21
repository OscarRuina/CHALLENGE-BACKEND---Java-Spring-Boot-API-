package com.alkemy.challenge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.alkemy.challenge.entities.Movie;
import com.alkemy.challenge.model.MovieModel;
import com.alkemy.challenge.services.MovieServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("GET/movies")
    public List<MovieModel> list(){
        List<MovieModel> models = new ArrayList<MovieModel>();
        for(Movie c : service.listAll()){
            models.add(new MovieModel(c.getTitulo(),c.getFechaCreacion()));
        }
        return models;
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> get(@PathVariable long id){
        try{
            Movie movie = service.get(id);
            return new ResponseEntity<Movie>(movie,HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movies")
    public void add(@RequestBody Movie movie){
        service.save(movie);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> get(@RequestBody Movie movie,@PathVariable long id){
        try{
            Movie existMovie = service.get(id);
            existMovie.setTitulo(movie.getTitulo());
            existMovie.setFechaCreacion(movie.getFechaCreacion());
            existMovie.setCalificacion(movie.getCalificacion());
            existMovie.setCharacters(movie.getCharacters());
            service.save(existMovie);
            return new ResponseEntity<>(HttpStatus.OK); 
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movies/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
    
    @GetMapping("/movies/name")
    public ResponseEntity<List<Movie>> getByName(@RequestParam(name = "name") String titulo){
        try{
            List<Movie> movies = service.getByName(titulo);
            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
        }
    }

}
