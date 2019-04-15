package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("email") String email,
                        @JsonProperty("password") String password) {
        this.email = email ;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
