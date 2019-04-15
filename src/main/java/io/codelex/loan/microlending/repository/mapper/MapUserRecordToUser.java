package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.model.UserRecord;

import java.util.function.Function;

public class MapUserRecordToUser implements Function<UserRecord, User> {

    @Override
    public User apply(UserRecord userRecord) {
        return new User(
                userRecord.getId(),
                userRecord.getUsername(),
                userRecord.getPassword(),
                userRecord.getFirstName(),
                userRecord.getLastName(),
                userRecord.getEmail()
        );
    }
}
