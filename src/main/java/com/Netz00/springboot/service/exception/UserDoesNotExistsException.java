package com.Netz00.springboot.service.exception;

public class UserDoesNotExistsException extends RuntimeException{
    public UserDoesNotExistsException(Long userID){
        super("User with ID " + userID + " does not exists");
    }
}
