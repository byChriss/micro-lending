package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public class SetTimeRequest {
    public LocalDateTime time;

    @JsonCreator
    public SetTimeRequest(@JsonProperty("time") LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
