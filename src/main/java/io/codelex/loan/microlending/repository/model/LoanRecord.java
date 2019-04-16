package io.codelex.loan.microlending.repository.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "loans")
public class LoanRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private Long term;
    private LocalDate approvalDate;
    private LocalDate repaymentDate;
    private BigDecimal extendAmount;
    private boolean status;
    @ManyToOne
    private UserRecord owner;

    public LoanRecord() {
    }

    public LoanRecord(Long amount, Long term, LocalDate approvalDate, LocalDate repaymentDate, BigDecimal extendAmount, boolean status, UserRecord owner) {
        this.amount = amount;
        this.term = term;
        this.approvalDate = approvalDate;
        this.repaymentDate = repaymentDate;
        this.extendAmount = extendAmount;
        this.status = status;
        this.owner = owner;
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

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public BigDecimal getExtendAmount() {
        return extendAmount;
    }

    public void setExtendAmount(BigDecimal extendAmount) {
        this.extendAmount = extendAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserRecord getOwner() {
        return owner;
    }

    public void setOwner(UserRecord owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRecord record = (LoanRecord) o;
        return id.equals(record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
