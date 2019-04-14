package io.codelex.loan.microlending.inmemory;

import io.codelex.loan.microlending.UserService;
import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
@ConditionalOnProperty(prefix = "micro-lending", name = "store-type", havingValue = "in-memory")
public class InMemoryUserService implements UserService {
    
    private AtomicLong userID = new AtomicLong();
    private List<User> users = new ArrayList<>();
    
    @Override
    public User createUser(CreateUserRequest request){
        User user = new User(
                userID.getAndIncrement(),
                request.getUsername(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
                
        );
        users.add(user);
        return user;
    }
    
    @Override
    public User findUser(Long id){
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
