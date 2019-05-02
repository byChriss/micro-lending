package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LoanRequest {
   
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Integer days;
    
    @JsonCreator
    public LoanRequest(@JsonProperty("amount") BigDecimal amount,
                       @JsonProperty("days") Integer days) {
        this.amount = amount;
        this.days = days;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getDays() {
        return days;
    }
}
