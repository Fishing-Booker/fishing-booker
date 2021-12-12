package com.example.fishingbooker.Exception;

public class ResourceConflictException extends RuntimeException{
    private static final long serialVersionUID = 1791564636123821405L;

    private String resourceUsername;

    public ResourceConflictException(String username, String message) {
        super(message);
        this.setResourceUsername(username);
    }

    public String getResourceUsername() {
        return resourceUsername;
    }

    public void setResourceUsername(String resourceUsername) {
        this.resourceUsername = resourceUsername;
    }
}
