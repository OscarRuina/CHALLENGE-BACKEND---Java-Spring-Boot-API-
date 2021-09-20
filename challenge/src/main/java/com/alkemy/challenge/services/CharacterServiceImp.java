package com.alkemy.challenge.services;

import java.util.List;

import com.alkemy.challenge.entities.Character;
import com.alkemy.challenge.repositories.ICharacterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterServiceImp implements ICharacterService{
    
    @Autowired
    private ICharacterRepository repo;

    @Transactional(readOnly = true)
    public List<Character> listAll(){
        return repo.findAll();
    }

    @Transactional
    public void save(Character character){
        repo.save(character);
    }

    @Transactional(readOnly = true)
    public Character get(long id){
        return repo.findById(id).get();
    }

    @Transactional
    public void delete(long id){
        repo.deleteById(id);
    }
}
