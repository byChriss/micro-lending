package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.CreateUserRequest;
import io.codelex.loan.microlending.api.LoginRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.authorisation.service.AuthService;
import io.codelex.loan.microlending.authorisation.service.Role;
import io.codelex.loan.microlending.repository.service.RepositoryUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final AuthService authService;
    private final RepositoryUserService service;

    public LoginController(AuthService authService, RepositoryUserService service) {
        this.authService = authService;
        this.service = service;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@Valid @RequestBody LoginRequest request) {
        try {
            User user = service.checkIfUserExists(request, request.getPassword());
            authService.authorise(request.getEmail(), request.getPassword(), Role.CUSTOMER);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody CreateUserRequest request) {
        try {
            authService.authorise(request.getEmail(), request.getPassword(), Role.CUSTOMER);
            return new ResponseEntity<>(service.createUser(request), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
      
    }

    @PostMapping("/sign-out")
    public void signOut() {
        authService.clearAuthentication();
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }
}
