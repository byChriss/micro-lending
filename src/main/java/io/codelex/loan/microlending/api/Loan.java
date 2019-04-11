package io.codelex.loan.microlending.api;

import java.time.LocalDate;

public class Loan {
    
    private Long id;
    /*private User userId;*/
    private Long amount;
    private LocalDate term;
    private boolean status;

    public Loan(Long id, Long amount, LocalDate term, boolean status) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
