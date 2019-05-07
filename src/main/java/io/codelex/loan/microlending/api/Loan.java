package io.codelex.loan.microlending.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Loan {

    private String id;
    private LoanStatus status;
    private LocalDate created;
    private LocalDate dueDate;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal total;
    private List<LoanExtension> extensions;
    


    public Loan(String id, LoanStatus status, LocalDate created, LocalDate dueDate, BigDecimal principal, BigDecimal interest, BigDecimal total, List<LoanExtension> extensions) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.dueDate = dueDate;
        this.principal = principal;
        this.interest = interest;
        this.total = total;
        this.extensions = extensions;
       
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

    public List<LoanExtension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<LoanExtension> extensions) {
        this.extensions = extensions;
    }
    
}
