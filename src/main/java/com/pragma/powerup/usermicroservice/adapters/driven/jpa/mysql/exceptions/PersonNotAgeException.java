package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions;



public class PersonNotAgeException extends RuntimeException{
    public PersonNotAgeException(String message) {
        super (message);
    }
}
