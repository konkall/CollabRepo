package com.appinit.appinit.controller;

import com.appinit.appinit.model.User;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id){
        super(("Could not find user "+id));

    }
}
