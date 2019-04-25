package io.codelex.loan.microlending.api;

import java.math.BigDecimal;

public class Application {
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private Status status;

    public Application(Long id, BigDecimal amount, Integer term, Status status) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
