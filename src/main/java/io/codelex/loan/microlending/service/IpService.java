package io.codelex.loan.microlending.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class IpService {
    private List<String> ipList = new ArrayList<>();
    private int equalIpCounter = 0;

    void addIp(HttpServletRequest servletRequest) {
        ipList.add(getClientIp(servletRequest));
    }

    private static String getClientIp(HttpServletRequest servletRequest) {

        String remoteAddr = "";

        if (servletRequest != null) {
            remoteAddr = servletRequest.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = servletRequest.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    boolean maxAttemptsFromIpReached() {
        for (int i = 0; i < ipList.size(); i++) {
            String part1 = ipList.get(i);
            for (int j = i + 1; j < ipList.size(); j++) {
                String part2 = ipList.get(j);
                if (part1.equals(part2)) {
                    equalIpCounter++;
                }
            }
        }
        if (equalIpCounter == 3) {
            return true;
        }
        return false;
    }

    @Scheduled(cron = "0 0 * * * *")
    void removeAttemptsAfterDay() {
        ipList.clear();
    }
}
