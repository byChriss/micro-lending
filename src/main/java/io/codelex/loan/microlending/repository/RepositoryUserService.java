package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.UserService;
import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(prefix = "micro-lending", name = "store-type", havingValue = "database")
public class RepositoryUserService implements UserService {
    private final MapUserRecordToUser toUser = new MapUserRecordToUser();
    private final UserRecordRepository userRecordRepository;

    public RepositoryUserService(UserRecordRepository userRecordRepository) {
        this.userRecordRepository = userRecordRepository;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        UserRecord userRecord = new UserRecord(
                request.getUsername(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail());
        
        userRecord = userRecordRepository.save(userRecord);
        return toUser.apply(userRecord);
    }

    @Override
    public User findUser(Long id) {
        return null;
    }
}
