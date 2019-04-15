package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.LoginRequest;
import io.codelex.loan.microlending.api.User;

public interface UserService {
    User createUser(CreateUserRequest request);

    User checkIfUserExists(LoginRequest request);
}