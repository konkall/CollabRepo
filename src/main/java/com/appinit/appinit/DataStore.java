package com.appinit.appinit;

public class DataStore {



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
