package io.codelex.loan.microlending.api;

import java.time.LocalDate;

public class LoanExtension {
    private Long id;
    private Long extensionDays;
    private LocalDate extensionDate;
    private LocalDate paybackDate;
    private Loan loanId;
    private boolean status;

    public LoanExtension(Long id, Long extensionDays, LocalDate extensionDate, LocalDate paybackDate, Loan loanId, boolean status) {
        this.id = id;
        this.extensionDays = extensionDays;
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

    public Long getExtensionDays() {
        return extensionDays;
    }

    public void setExtensionDays(Long extensionDays) {
        this.extensionDays = extensionDays;
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

    public Loan getLoanId() {
        return loanId;
    }

    public void setLoanId(Loan loanId) {
        this.loanId = loanId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
