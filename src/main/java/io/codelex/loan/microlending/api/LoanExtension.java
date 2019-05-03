package io.codelex.loan.microlending.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanExtension {
    private Long id;
    private LocalDate created;
    private Integer days;
    private BigDecimal interest;
    private Loan loanId;


    public LoanExtension(Long id, LocalDate created, Integer days, BigDecimal interest, Loan loanId) {
        this.id = id;
        this.created = created;
        this.days = days;
        this.interest = interest;
        this.loanId = loanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Loan getLoanId() {
        return loanId;
    }

    public void setLoanId(Loan loanId) {
        this.loanId = loanId;
    }
}
