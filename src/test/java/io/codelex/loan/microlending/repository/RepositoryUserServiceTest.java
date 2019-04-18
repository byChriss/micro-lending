package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.*;

import io.codelex.loan.microlending.repository.mapper.MapUserRecordToUser;

import io.codelex.loan.microlending.repository.model.UserRecord;
import io.codelex.loan.microlending.repository.service.RepositoryUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class RepositoryUserServiceTest {
    private UserRecordRepository userRecordRepository = Mockito.mock(UserRecordRepository.class);
    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private RepositoryUserService service = new RepositoryUserService(userRecordRepository, passwordEncoder);
    private MapUserRecordToUser toUser = Mockito.mock(MapUserRecordToUser.class);
    private UserRecord userRecord = createUserRecord();
    private LoginRequest loginRequest = createLoginRequest();
    private CreateUserRequest createUserRequest = createUserRequest();

    @Test
    void should_be_able_to_create_user() {
        //given
        User user = createUser();
        //when
        Mockito.when(userRecordRepository.save(any()))
                .thenReturn(userRecord);
        Mockito.when(toUser.apply(any()))
                .thenReturn(user);
        User result = service.createUser(createUserRequest);
        //then
        Assert.assertEquals(user.getUsername(), result.getUsername());
        Assert.assertEquals(user.getEmail(), result.getEmail());

    }
//ToDO
/*    @Test
    void should_be_able_find_user_exists() {
        //given
        User user = createUser();
        //when
        Mockito.when(userRecordRepository.findByEmail(any()))
                .thenReturn(userRecord);
        Mockito.when(userRecordRepository.save(any()))
                .thenReturn(userRecord);
        Mockito.when(toUser.apply(any()))
                .thenReturn(user);
        
        User result = service.checkIfUserExists(loginRequest);
        //then
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }*/
    //toDo wont work!
    @Test
    void should_not_be_able_creat_user_if_username_is_taken() {
        //when
        Mockito.when(userRecordRepository.isUserPresent(any()))
                .thenReturn(true);
        User user = service.createUser(createUserRequest);
        //then
        assertThrows(IllegalArgumentException.class, (Executable) user);
    }

    private CreateUserRequest createUserRequest() {
        return new CreateUserRequest(
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"
        );
    }

    private UserRecord createUserRecord() {
        return new UserRecord(
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"
        );
    }

    private User createUser() {
        return new User(
                1L,
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"
        );
    }

    private LoginRequest createLoginRequest() {
        return new LoginRequest(
                "smt@gmail.com",
                "12345"
        );
    }

}