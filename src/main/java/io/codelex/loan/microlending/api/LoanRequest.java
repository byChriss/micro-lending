package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class LoanRequest {
    
    @NotNull
    private Long amount;
    @NotNull
    private Long term;

    public LoanRequest(@JsonProperty("amount") Long amount, 
                       @JsonProperty("term") Long term) {
        this.amount = amount;
        this.term = term;
    }

    public Long getAmount() {
        return amount;
    }
    public Long getTerm() {
        return term;
    }


}
