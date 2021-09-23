package com.alkemy.challenge.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.alkemy.challenge.entities.Genre;
import com.alkemy.challenge.entities.Movie;
import com.alkemy.challenge.services.GenreServiceImp;
import com.alkemy.challenge.services.MovieServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {

    @Autowired
    private GenreServiceImp service;

    @Autowired
    private MovieServiceImp movieService;

    @GetMapping("/genres")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Genre> list(){
        return service.listAll();
    }

    @PostMapping("/genres")
    @PreAuthorize("hasRole('ADMIN')")
    public void add(@RequestBody Genre genre){
        service.save(genre);
    }

    @PutMapping("/genres/{id}/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovies(@RequestBody Movie movie,@PathVariable long id){
        try{
            Genre existGenre = service.get(id);
            Movie existMovie = movieService.get(movie.getIdMovie());
            existGenre.getMovies().add(existMovie);
            service.save(existGenre);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
