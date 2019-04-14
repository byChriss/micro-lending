/*
package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.inmemory.InMemoryUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-api")
public class UserController {
    
    private final InMemoryUserService userService;

    public UserController(InMemoryUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    public ResponseEntity<User> createUserRequest(@RequestBody CreateUserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
        
    } 
    
}
*/
