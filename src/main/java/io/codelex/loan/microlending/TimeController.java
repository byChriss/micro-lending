package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.SetTimeRequest;
import io.codelex.loan.microlending.repository.service.ClockTime;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/testing-api")
public class TimeController {
    private ClockTime clockTime;


    public TimeController(ClockTime clockTime) {
        this.clockTime = clockTime;
    }

    @PostMapping("/reset-time")
    public void resetTime(){
        clockTime.setTime(LocalDateTime.now());
    }


    @PutMapping("/time")
    public void setTime(@RequestBody SetTimeRequest request){
       /* *//*String str = request.getTime();*//*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);*/
        clockTime.setTime(request.getTime());
    }

    @GetMapping("/time")
    public  ResponseEntity<LocalDateTime> returnsTime(){
        LocalDateTime clockTime1 = clockTime.getTime();
        return new ResponseEntity<>(clockTime1, HttpStatus.OK);
    }
}
