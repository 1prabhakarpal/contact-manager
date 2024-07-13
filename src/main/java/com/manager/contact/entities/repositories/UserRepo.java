package com.manager.contact.entities.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.contact.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
    //Methods for basic CRUD operations
    //Extra methods DB related operations
    //custom finder methods
    Optional<User> findByEmail(String email);
    //custom query methods
}
