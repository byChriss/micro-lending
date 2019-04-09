package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class LoanRequest {
    
    @NotNull
    private Long amount;
    @NotNull
    private LocalDate term;

    public LoanRequest(@JsonProperty("amount") Long amount, 
                       @JsonProperty("term") LocalDate term) {
        this.amount = amount;
        this.term = term;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }
}
