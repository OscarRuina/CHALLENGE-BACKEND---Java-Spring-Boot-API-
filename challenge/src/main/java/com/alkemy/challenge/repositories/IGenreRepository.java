package com.alkemy.challenge.repositories;

import com.alkemy.challenge.entities.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre,Long>{
    
}
