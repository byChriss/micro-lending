package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    
    public CreateUserRequest(@JsonProperty("username") String username,
                             @JsonProperty("password") String password,
                             @JsonProperty("firstName") String firstName,
                             @JsonProperty("lastName") String lastName,
                             @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
