package com.appinit.appinit.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "ResetPasswordToken")
public class ResetPasswordToken {


    @Id @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name="token_id")
    private long tokenid;

    @Column(name="reset_password_token")
    private String resetPassToken;

    @Column(name="active")
    private boolean active;



    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ResetPasswordToken() {
    }

    public  ResetPasswordToken(User user) {
        this.user = user;
        createdDate = new Date();
        resetPassToken = UUID.randomUUID().toString();
        setActive(true);
    }


    public Long getTokenId() {
        return tokenid;
    }

    public void setTokenid(Long id) {
        this.tokenid = id;
    }

    public String getResetPassToken() {
        return resetPassToken;
    }

    public void setResetPassToken(String resetPassToken) {
        this.resetPassToken = resetPassToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public Boolean getActive(){
        return this.active;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

}
