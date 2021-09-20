package com.alkemy.challenge.services;

import java.util.List;

import com.alkemy.challenge.entities.Movie;
import com.alkemy.challenge.repositories.IMovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImp implements IMovieService{

    @Autowired
    IMovieRepository repo;

   @Transactional(readOnly = true)
   public List<Movie> listAll(){
       return repo.findAll();
   }
   
   @Transactional
   public void save(Movie movie){
       repo.save(movie);
   }

   @Transactional(readOnly = true)
   public Movie get(long id){
       return repo.findById(id).get();
   }

   @Transactional
   public void delete(long id){
       repo.deleteById(id);
   }

}
