package com.appinit.appinit.repository;

import com.appinit.appinit.model.ConfirmationToken;
import com.appinit.appinit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,String>
{
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}


