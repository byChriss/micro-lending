package io.codelex.loan.microlending.repository.model;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Double extendAmount;
    private boolean status;
    @ManyToOne
    private UserRecord owner;


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

    public Double getExtendAmount() {
        return extendAmount;
    }

    public void setExtendAmount(Double extendAmount) {
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
}
