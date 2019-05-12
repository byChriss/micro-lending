package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.*;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


public interface LoanService {

    Loan createLoan(Principal principal, LoanRequest request, HttpServletRequest servletRequest);

    Loan findByIdAndExtend(Principal principal, String id, Integer days);

    LoanExtension createLoanExtension(String id, Integer days);

    List<ExtensionRecord> getLoansWithExtensions(Long id);
   
    Application checkApplication(Principal principal, LoanRequest request, HttpServletRequest servletRequest);

    Constraints setConstraints();

    List<Loan> findLoan(String owner);

    boolean checkIfAmountIsValid(LoanRequest request);

    boolean checkIfTermIsValid(LoanRequest request);
}
