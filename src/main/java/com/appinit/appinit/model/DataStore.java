package com.appinit.appinit.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "kostas") //POJO=Plain Old Java Object = When we talk about a POJO, what we're describing is a straightforward type with no references to any particular frameworks
//Entity are data in a database..this entity represents a table called kostas and has two parameters. 1) firstname 2)lastname
//An entity needs an id . In this case the id is firstname.
public class DataStore {
    @Id
    private String firstname;

    private String lastname;


    public void setFirstname(String fs){
        this.firstname = fs;
    }

    public void setLastname(String ls){
        this.lastname = ls;
    }


    public String getFirstname(){

        return firstname;

    }

    public String getLastname(){

        return lastname;
    }


}
