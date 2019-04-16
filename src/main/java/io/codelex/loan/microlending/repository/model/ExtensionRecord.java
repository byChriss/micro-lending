package io.codelex.loan.microlending.repository.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "extensions")
public class ExtensionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long extendDays;
    private LocalDate extensionDate;
    private LocalDate paybackDate;
    @ManyToOne
    private LoanRecord loanId;
    private boolean status;

    public ExtensionRecord() {
    }
//todo
    public ExtensionRecord(Long extendDays, LocalDate extensionDate, LocalDate paybackDate, LoanRecord loanId, boolean status) {
        this.extendDays = extendDays;
        this.extensionDate = extensionDate;
        this.paybackDate = paybackDate;
        this.loanId = loanId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExtendDays() {
        return extendDays;
    }

    public void setExtendDays(Long extendDays) {
        this.extendDays = extendDays;
    }

    public LocalDate getExtensionDate() {
        return extensionDate;
    }

    public void setExtensionDate(LocalDate extensionDate) {
        this.extensionDate = extensionDate;
    }

    public LocalDate getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(LocalDate paybackDate) {
        this.paybackDate = paybackDate;
    }

    public LoanRecord getLoanId() {
        return loanId;
    }

    public void setLoanId(LoanRecord loanId) {
        this.loanId = loanId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
