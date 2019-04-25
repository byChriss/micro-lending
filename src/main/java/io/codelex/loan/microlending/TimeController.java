package io.codelex.loan.microlending;

import io.codelex.loan.microlending.repository.LoanExtensionRecordRepository;
import io.codelex.loan.microlending.repository.LoanRecordRepository;
import io.codelex.loan.microlending.repository.UserRecordRepository;
import io.codelex.loan.microlending.repository.service.ClockTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/testing-api")
public class TimeController {
    private ClockTime clockTime;
    private final LoanRecordRepository loanRecordRepository;
    private final LoanExtensionRecordRepository extensionRecordRepository;
    private final UserRecordRepository userRecordRepository;


    public TimeController(ClockTime clockTime, LoanRecordRepository loanRecordRepository, LoanExtensionRecordRepository extensionRecordRepository, UserRecordRepository userRecordRepository) {
        this.clockTime = clockTime;
        this.loanRecordRepository = loanRecordRepository;
        this.extensionRecordRepository = extensionRecordRepository;
        this.userRecordRepository = userRecordRepository;
    }

    @PostMapping("/reset-time")
    public void resetTime(){
        clockTime.setTime(LocalDateTime.now());
    }


    @PutMapping("/time")
    public void setTime(@RequestBody LocalDateTime request){
        clockTime.setTime(request);
    }

    @GetMapping("/time")
    public  ResponseEntity<LocalDateTime> returnsTime(){
        LocalDateTime clockTime1 = clockTime.getTime();
        return new ResponseEntity<>(clockTime1, HttpStatus.OK);
    }
    @PostMapping("/clear-database")
    public void clearDatabase(){
        loanRecordRepository.deleteAll();
        extensionRecordRepository.deleteAll();
        userRecordRepository.deleteAll();
    }
}
