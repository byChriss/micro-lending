package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.IpService;

import javax.servlet.http.HttpServletRequest;

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
