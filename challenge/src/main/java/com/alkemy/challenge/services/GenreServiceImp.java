package com.alkemy.challenge.services;

import java.util.List;

import com.alkemy.challenge.entities.Genre;
import com.alkemy.challenge.repositories.IGenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreServiceImp {

    @Autowired
    private IGenreRepository repo;

    @Transactional(readOnly = true)
    public List<Genre> listAll(){
        return repo.findAll();
    }

    @Transactional
    public void save(Genre genre){
        repo.save(genre);
    }

    @Transactional(readOnly = true)
    public Genre get(long id){
        return repo.findById(id).get();
    }

    @Transactional
    public void delete(long id){
        repo.deleteById(id);
    }
    
}
