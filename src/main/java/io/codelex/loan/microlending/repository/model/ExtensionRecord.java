package io.codelex.loan.microlending.repository.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "extensions")
public class ExtensionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;
    private Integer days;
    private BigDecimal interest;
    @ManyToOne
    private LoanRecord loanId;

    public ExtensionRecord() {
    }

    public ExtensionRecord(LocalDate created, Integer days, BigDecimal interest, LoanRecord loanId) {
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

    public LoanRecord getLoanId() {
        return loanId;
    }

    public void setLoanId(LoanRecord loanId) {
        this.loanId = loanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtensionRecord that = (ExtensionRecord) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
