package io.codelex.loan.microlending.authorisation.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.Collections.singleton;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthService {

    public void authorise(String email, String password, Role role) {
        Set<SimpleGrantedAuthority> authorities = singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password, authorities);
        getContext().setAuthentication(token);
    }

    public void clearAuthentication() {
        getContext().setAuthentication(null);
    }

}
