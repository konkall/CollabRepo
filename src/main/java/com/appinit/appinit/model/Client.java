package com.appinit.appinit.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity (name = "client") //POJO=Plain Old Java Object = When we talk about a POJO, what we're describing is a straightforward type with no references to any particular frameworks
//Entity are data in a database..this entity represents a table called kostas and has two parameters. 1) firstname 2)lastname
//An entity needs an id . In this case the id is firstname.
public class Client {
    @Id @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private long id;
    private String name;
    private String ssn;
    private String accountType;
    private double balance;


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
}
