package com.appinit.appinit.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name ="LoginCredentials") //POJO=Plain Old Java Object = When we talk about a POJO, what we're describing is a straightforward type with no references to any particular frameworks
//Entity are data in a database..this entity represents a table called kostas and has two parameters. 1) firstname 2)lastname
//An entity needs an id . In this case the id is firstname.
public class DataStore {
    @Id
    @Column(unique=true)
    @NotNull
    private String email;

    private String firstname;

    private String lastname;


    @Size(min=8)
    private String password;

    @Transient
    private String repassword;


    public void setEmail(String em){
        this.email = em;
    }

    public void setPassword(String pas){
        this.password = pas;
    }

    public void setRepassword(String repas){
        this.repassword = repas;
    }


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
    public String getEmail(){

        return email;
    }

    public String getPassword(){

        return password;
    }

    public String getRepassword(){

        return repassword;
    }




}
