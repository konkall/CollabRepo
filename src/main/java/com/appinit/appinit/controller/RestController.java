package com.appinit.appinit.controller;


import com.appinit.appinit.model.User;
import com.appinit.appinit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/users")
    List<User> all(){

        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/users/{id}")
    User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(employee -> {
                    employee.setUsername(newUser.getUsername());
                    employee.setPassword(newUser.getPassword());
                    employee.setEmail(newUser.getEmail());
                    return userRepository.save(employee);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }


    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);

       // return ResponseEntity.noContent().build();
    }





}
