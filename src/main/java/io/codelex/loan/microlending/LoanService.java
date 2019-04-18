package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface LoanService {

    Loan createLoan(String owner, LoanRequest request, HttpServletRequest servletRequest);

    Loan findByIdAndExtend(Long id, Long days);

    LoanExtension createLoanExtension(Long id, Long days);

    List<LoanRecord> findAllLoansByUserEmail(String owner);

    List<ExtensionRecord> getLoansWithExtensions(Long id);
}
