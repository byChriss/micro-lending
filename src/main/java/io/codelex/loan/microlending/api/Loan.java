package io.codelex.loan.microlending.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan {

    private Long id;
    private BigDecimal amount;
    private Integer term;
    private LocalDateTime approvalDate;
    private LocalDateTime repaymentsDate;
    private BigDecimal extendAmount;
    private User owner;
    private boolean status;

    public Loan(Long id, BigDecimal amount, Integer term, LocalDateTime approvalDate, LocalDateTime repaymentsDate, BigDecimal extendAmount, User owner, boolean status) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.approvalDate = approvalDate;
        this.repaymentsDate = repaymentsDate;
        this.extendAmount = extendAmount;
        this.owner = owner;
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

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDateTime getRepaymentsDate() {
        return repaymentsDate;
    }

    public void setRepaymentsDate(LocalDateTime repaymentsDate) {
        this.repaymentsDate = repaymentsDate;
    }

    public BigDecimal getExtendAmount() {
        return extendAmount;
    }

    public void setExtendAmount(BigDecimal extendAmount) {
        this.extendAmount = extendAmount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
