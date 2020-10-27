package com.appinit.appinit.repository;

import com.appinit.appinit.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  ClientRepository extends JpaRepository<Client, Long>{


    Iterable<Client> findAllByNameContaining(String name);
    Iterable<Client> findAllBySsn(String ssn);
    Iterable<Client> findAllByAccountType(String type);

    Iterable<Client> findAllByNameAndSsn(String name, String ssn);
    Iterable<Client> findAllByNameAndAccountType(String name, String type);
    Iterable<Client> findAllBySsnAndAccountType(String ssn, String type);

    Iterable<Client> findAllByNameAndSsnAndAccountType(String name, String ssn, String type);



}
