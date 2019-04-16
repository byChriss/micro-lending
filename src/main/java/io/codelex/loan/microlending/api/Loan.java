package io.codelex.loan.microlending.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {

    private Long id;
    private Long amount;
    private Long term;
    private LocalDate approvalDate;
    private LocalDate repaymentsDate;
    private BigDecimal extendAmount;
    private User owner;
    private boolean status;

    public Loan(Long id, Long amount, Long term, LocalDate approvalDate, LocalDate repaymentsDate, BigDecimal extendAmount, User owner, boolean status) {
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

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getRepaymentsDate() {
        return repaymentsDate;
    }

    public void setRepaymentsDate(LocalDate repaymentsDate) {
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
