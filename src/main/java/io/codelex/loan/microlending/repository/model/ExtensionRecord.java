package io.codelex.loan.microlending.repository.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "extensions")
public class ExtensionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long extendDays;
    private LocalDateTime extensionDate;
    private LocalDateTime paybackDate;
    @ManyToOne
    private LoanRecord loanId;
    private boolean status;

    public ExtensionRecord() {
    }

    public ExtensionRecord(Long extendDays, LocalDateTime extensionDate, LocalDateTime paybackDate, LoanRecord loanId, boolean status) {
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

    public LocalDateTime getExtensionDate() {
        return extensionDate;
    }

    public LocalDateTime getPaybackDate() {
        return paybackDate;
    }

    public LoanRecord getLoanId() {
        return loanId;
    }

    public boolean isStatus() {
        return status;
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
