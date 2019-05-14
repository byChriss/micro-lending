package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterRequest {
    @NotEmpty
    private String password;
    @Email
    @NotEmpty
    private String email;

    @JsonCreator
    public RegisterRequest(@JsonProperty("password") String password,
                           @JsonProperty("email") String email) {
        
        this.password = password;
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
