package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;

import javax.servlet.http.HttpServletRequest;

public interface LoanService {

    Loan createLoan(LoanRequest request, HttpServletRequest servletRequest);

    Loan findByIdAndExtend(Long id, Long days);
    
    boolean isLoanPresent(Long id);

    boolean checkTime();

 
}
