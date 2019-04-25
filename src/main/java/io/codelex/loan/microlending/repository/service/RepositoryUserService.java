package io.codelex.loan.microlending.repository.service;

import io.codelex.loan.microlending.UserService;
import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.LoginRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.UserRecordRepository;
import io.codelex.loan.microlending.repository.mapper.MapUserRecordToUser;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
public class RepositoryUserService implements UserService {
    private final MapUserRecordToUser toUser = new MapUserRecordToUser();
    private final UserRecordRepository userRecordRepository;
    private PasswordEncoder passwordEncoder;

    public RepositoryUserService(UserRecordRepository userRecordRepository, PasswordEncoder passwordEncoder) {
        this.userRecordRepository = userRecordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        if (userRecordRepository.isUserPresent(request.getEmail())) {
            throw new IllegalArgumentException("username is taken");
        }
        if(checkIfPasswordIsValid(request)){
            throw new IllegalArgumentException("Invalid password content");
        }
            UserRecord userRecord = new UserRecord(
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail());

            userRecord = userRecordRepository.save(userRecord);
            return toUser.apply(userRecord);
       
    }

    @Override
    public User checkIfUserExists(LoginRequest request, String password) {
        UserRecord record = userRecordRepository.findByEmail(request.getEmail());
        if (record != null && isPasswordMatching(record, password)) {
            return toUser.apply(record);
        }
        throw new NoSuchElementException("No such a user");
    }

    private boolean isPasswordMatching(UserRecord userRecord, String password) {
        return passwordEncoder.matches(password, userRecord.getPassword());

    }
    
    private boolean checkIfPasswordIsValid(CreateUserRequest request){
        if (request.getPassword().trim().isEmpty() || request.getPassword().length() <= 3){
            return true;
        }
        return false;
    }
}
