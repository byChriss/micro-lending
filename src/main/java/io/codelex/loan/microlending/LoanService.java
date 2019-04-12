package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;

import javax.servlet.http.HttpServletRequest;

public interface LoanService {

    Loan createLoan(LoanRequest request, HttpServletRequest servletRequest);

    Loan findByIdAndExtend(Long id, Long days);

    LoanExtension createLoanExtension(Long id, Long days);

    Loan findLoanById(Long id);
}
