package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.IpService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
@ConditionalOnProperty(prefix = "micro-lending", name = "store-type", havingValue = "database")
public class RepositoryIpService implements IpService {

    @Override
    public void addIp(HttpServletRequest servletRequest) {
        
    }

    @Override
    public String getClientIp(HttpServletRequest servletRequest) {
        return null;
    }

    @Override
    public boolean maxAttemptsFromIpReached() {
        return false;
    }

    @Override
    public void removeAttemptsAfterDay() {

    }
}
