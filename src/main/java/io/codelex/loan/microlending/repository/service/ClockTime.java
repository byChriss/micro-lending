package io.codelex.loan.microlending.repository.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ClockTime {
    public LocalDateTime time;

    public ClockTime() {
    }


    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
