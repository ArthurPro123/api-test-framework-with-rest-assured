package com.codesn.services.jsonplaceholder.datatemplates;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserPayload {

    @JsonProperty private String name;
    @JsonProperty private String email;
    @JsonProperty private String username;

    // Default constructor required by Jackson
    public UserPayload() {}

    // Constructor for easy creation
    public UserPayload(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }


    // Getters and Setters (required by Jackson for serialization/deserialization)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

