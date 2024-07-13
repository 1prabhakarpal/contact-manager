package com.manager.contact.helper;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(){
        super("Resource not found");
    }
}
