package com.codesn.services.booking.datatemplates;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthPayload {

  @JsonProperty private String username;
  @JsonProperty private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public AuthPayload(String username, String password) {
    this.username = username;
    this.password = password;
  }
}

