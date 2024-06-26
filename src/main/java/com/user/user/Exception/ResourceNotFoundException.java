package com.user.user.Exception;

public class ResourceNotFoundException extends  RuntimeException {
    private String message;


    public ResourceNotFoundException(String message) {
      super(message);
    }
}
