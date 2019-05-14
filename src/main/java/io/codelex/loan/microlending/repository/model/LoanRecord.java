package io.codelex.loan.microlending.repository.model;
import io.codelex.loan.microlending.api.LoanStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "loans")
public class LoanRecord {
    @Id
    private String id;
    private LoanStatus status;
    private LocalDate created;
    private LocalDate dueDate;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal total;
    @OneToMany
    private List<ExtensionRecord> extensions;
    @ManyToOne
    private UserRecord owner;

    public LoanRecord() {
    }

    public LoanRecord(String id, LoanStatus status, LocalDate created, LocalDate dueDate, BigDecimal principal, BigDecimal interest, BigDecimal total, List<ExtensionRecord> extensions, UserRecord owner) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.dueDate = dueDate;
        this.principal = principal;
        this.interest = interest;
        this.total = total;
        this.extensions = extensions;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ExtensionRecord> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<ExtensionRecord> extensions) {
        this.extensions = extensions;
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
