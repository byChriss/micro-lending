package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LoanRequest {
   
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Integer term;
    
    @JsonCreator
    public LoanRequest(@JsonProperty("amount") BigDecimal amount,
                       @JsonProperty("term") Integer term) {
        this.amount = amount;
        this.term = term;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTerm() {
        return term;
    }
}
