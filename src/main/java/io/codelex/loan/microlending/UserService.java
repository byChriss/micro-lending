package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.RegisterRequest;
import io.codelex.loan.microlending.api.LoginRequest;
import io.codelex.loan.microlending.api.User;

public interface UserService {
    User createUser(RegisterRequest request);

    User checkIfUserExists(LoginRequest request, String password);
    
}
