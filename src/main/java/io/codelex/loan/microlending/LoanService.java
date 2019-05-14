package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.*;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.List;


public interface LoanService {

    Loan createLoan(Principal principal, LoanRequest request, HttpServletRequest servletRequest);

    Loan findByIdAndExtend(Principal principal, String id, Integer days);

    LoanExtension createLoanExtension(String id, Integer days);

    List<ExtensionRecord> findLoansWithExtensions(Long id);
   
    Application application(Principal principal, LoanRequest request, HttpServletRequest servletRequest);

    Constraints setConstraints();

    List<Loan> findLoan(String owner);

    boolean amountValidation(LoanRequest request);

    boolean termValidation(LoanRequest request);

}
