package com.appinit.appinit.repository;

import com.appinit.appinit.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Spring Data = project where you can connect your program with databases
//interface is an absctract class..cannot have a body because you cannot create objects
//crud stands for Create Read Update Delete
// 1 argument = Entity
// 2 argumnet = type tou id
public interface  UserRepository extends JpaRepository< User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPassword(String password);
    Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);

    User findAllByEmail(String email);
}
