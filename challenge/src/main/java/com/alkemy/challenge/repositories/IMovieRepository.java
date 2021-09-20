package com.alkemy.challenge.repositories;

import com.alkemy.challenge.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long>{
    
}
