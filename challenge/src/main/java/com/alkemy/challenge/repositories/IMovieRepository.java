package com.alkemy.challenge.repositories;

import java.util.List;

import com.alkemy.challenge.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long>{

    public List<Movie> findByTitulo(String titulo);

}
