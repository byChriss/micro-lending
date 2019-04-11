package io.codelex.loan.microlending.api;

public class Loan {
    
    private Long id;
    /*private User userId;*/
    private Long amount;
    private Long term;
    private boolean status;

    public Loan(Long id, Long amount, Long term, boolean status) {
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

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
