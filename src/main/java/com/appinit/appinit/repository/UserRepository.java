package com.appinit.appinit.repository;

import com.appinit.appinit.model.DataStore;
import org.springframework.data.repository.CrudRepository;

//Spring Data = project where you can connect your program with databases
//interface is an absctract class..cannot have a body because you cannot create objects
//crud stands for Create Read Update Delete
// 1 argument = Entity
// 2 argumnet = type tou id
public interface  UserRepository extends CrudRepository<DataStore, String> {
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
}
