package com.application.fullStackCrud.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id){
        super("there is no user available for id"+" "+id);
    }
}
