package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.UserService;
import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.LoginRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.mapper.MapUserRecordToUser;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
public class RepositoryUserService implements UserService {
    private final MapUserRecordToUser toUser = new MapUserRecordToUser();
    private final UserRecordRepository userRecordRepository;

    public RepositoryUserService(UserRecordRepository userRecordRepository) {
        this.userRecordRepository = userRecordRepository;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        if (!userRecordRepository.isUserPresent(request.getUsername())) {
            UserRecord userRecord = new UserRecord(
                    request.getUsername(),
                    request.getPassword(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail());

            userRecord = userRecordRepository.save(userRecord);
            return toUser.apply(userRecord);
        }
        throw new IllegalArgumentException("username is taken");
    }

    @Override
    public User checkIfUserExists(LoginRequest request) {
        UserRecord record = userRecordRepository.finByEmail(request.getEmail());
        if (record != null) {
            return toUser.apply(record);
        }
        throw new NoSuchElementException("No such a user");
    }

}
