package com.appinit.appinit.repository;

import com.appinit.appinit.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface  ClientRepository extends CrudRepository<Client, Long> {
    Iterable<Client> findAllByNameContaining(String name);
    Iterable<Client> findAllBySsn(String ssn);
    Iterable<Client> findAllByAccountType(String type);

    Iterable<Client> findAllByNameAndSsn(String name, String ssn);
    Iterable<Client> findAllByNameAndAccountType(String name, String type);
    Iterable<Client> findAllBySsnAndAccountType(String ssn, String type);

    Iterable<Client> findAllByNameAndSsnAndAccountType(String name, String ssn, String type);

}
