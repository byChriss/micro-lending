package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.SetTimeRequest;
import io.codelex.loan.microlending.repository.service.ClockTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testing-api")
public class TimeController {
    private ClockTime clockTime;


    public TimeController(ClockTime clockTime) {
        this.clockTime = clockTime;
    }

    @PostMapping("/reset-time")
    public ResponseEntity resetTime(){
        return null;
    }
    
    @PutMapping("/time")
    public ResponseEntity setTime(@RequestBody SetTimeRequest request){
        if(request !=null){
            clockTime.setTime(request.getTime());
        }
       return null;
    }
    
}
