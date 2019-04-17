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
        User user = service.checkIfUserExists(request);
        authService.authorise(request.getEmail(), request.getPassword(), Role.CUSTOMER);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody CreateUserRequest request) {
        authService.authorise(request.getEmail(), request.getPassword(), Role.CUSTOMER);
        return new ResponseEntity<>(service.createUser(request), HttpStatus.CREATED);
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
