package com.bookmanagement.model.exceptions;

public class UsernameAndPasNotFoundException extends RuntimeException{
    public UsernameAndPasNotFoundException(){
        super("Username or password does not exist");
    }
}
