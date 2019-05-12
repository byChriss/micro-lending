package io.codelex.loan.microlending.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanExtension {
    private LocalDate created;
    private Integer days;
    private BigDecimal interest;


    public LoanExtension(LocalDate created, Integer days, BigDecimal interest) {
        this.created = created;
        this.days = days;
        this.interest = interest;

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

}
