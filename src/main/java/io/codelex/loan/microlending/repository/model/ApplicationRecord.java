package io.codelex.loan.microlending.repository.model;

import io.codelex.loan.microlending.api.ApplicationStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "applications")
public class ApplicationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private ApplicationStatus status;
    

    public ApplicationRecord(BigDecimal amount, Integer term, ApplicationStatus status) {
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

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
