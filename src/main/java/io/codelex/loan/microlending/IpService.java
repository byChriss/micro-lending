package io.codelex.loan.microlending;

import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;

public interface IpService{
    void addIp(HttpServletRequest servletRequest);

    String getClientIp(HttpServletRequest servletRequest);
    
    boolean maxAttemptsFromIpReached();
    
    @Scheduled(cron = "0 0 * * * *")
    void removeAttemptsAfterDay();
}
