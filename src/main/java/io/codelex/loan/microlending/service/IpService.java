package io.codelex.loan.microlending.service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class IpService {
     List<String> ipList = new ArrayList<>();
      int equalIpCounter = 0;

     void addIp(HttpServletRequest servletRequest) {
       ipList.add(getClientIp(servletRequest));
       
    }

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            
        }
        
        return remoteAddr;
    }

    boolean maxAttemptsFromIpReached() {
        for (int i = 0; i < ipList.size() ; i++) {
                String part1 = ipList.get(i);
            for (int j = i + 1; j < ipList.size(); j++){
                String part2 = ipList.get(j);
                if(part1.equals(part2)){
                    equalIpCounter++;
                }
            }
        }
        if(equalIpCounter == 3){
            return true;
        }
        return false;
    }
    
    void removeAttemptsAfterDay(){
         if(LocalTime.now().equals(LocalTime.MIDNIGHT)){
             ipList.clear();
         }
    }
}
