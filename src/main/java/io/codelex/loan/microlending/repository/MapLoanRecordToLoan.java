package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.repository.model.LoanRecord;

import java.util.function.Function;

class MapLoanRecordToLoan implements Function<LoanRecord, Loan> {
    private MapUserRecordToUser toUser = new MapUserRecordToUser();

    @Override
    public Loan apply(LoanRecord loanRecord) {
        return new Loan(
                loanRecord.getId(),
                loanRecord.getAmount(),
                loanRecord.getTerm(),
                loanRecord.getApprovalDate(),
                loanRecord.getRepaymentDate(),
                loanRecord.getExtendAmount(),
                toUser.apply(loanRecord.getOwner()),
                loanRecord.isStatus());
    }
}
