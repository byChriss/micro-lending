package io.codelex.loan.microlending.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class LoanRequest {
    private User id;
    @NotNull
    private Long amount;
    @NotNull
    private Long term;

    public LoanRequest(@JsonProperty("id") User id,
                       @JsonProperty("amount") Long amount,
                       @JsonProperty("term") Long term) {
        this.amount = amount;
        this.term = term;
    }

    public User getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getTerm() {
        return term;
    }
}
