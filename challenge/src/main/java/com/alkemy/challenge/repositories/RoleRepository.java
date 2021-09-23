package com.alkemy.challenge.repositories;

import java.util.Optional;

import com.alkemy.challenge.entities.Role;
import com.alkemy.challenge.entities.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

    public Optional<Role> findByName(RoleName roleName);
    
}
