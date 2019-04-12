package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.repository.model.LoanRecord;

import java.util.function.Function;

public class MapLoanRecordToLoan implements Function<LoanRecord, Loan> {

    @Override
    public Loan apply(LoanRecord loanRecord) {
        return null;
    }
}
