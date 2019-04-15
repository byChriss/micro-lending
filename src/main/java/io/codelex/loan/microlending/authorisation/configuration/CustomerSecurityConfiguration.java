package io.codelex.loan.microlending.authorisation.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static io.codelex.loan.microlending.authorisation.service.Role.CUSTOMER;

@Order(100)
@Configuration
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/sign-in", "/api/register").permitAll()
                .anyRequest().hasRole(CUSTOMER.name());
    }
}
