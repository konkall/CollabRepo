package com.appinit.appinit.repository;

import com.appinit.appinit.model.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken,String> {

    ResetPasswordToken findByResetPassToken(String resetPasswordToken);
    ResetPasswordToken findByUserId(long id);
    void deleteByUserId(long id);

}
