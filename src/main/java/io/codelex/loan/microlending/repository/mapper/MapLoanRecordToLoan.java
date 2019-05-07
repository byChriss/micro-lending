package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapLoanRecordToLoan implements Function<LoanRecord, Loan> {
    private MapExtensionRecordToExtension toExtension = new MapExtensionRecordToExtension();
    

    @Override
    public Loan apply(LoanRecord loanRecord) {
        return new Loan(
                loanRecord.getId(),
                loanRecord.getStatus(),
                loanRecord.getCreated(),
                loanRecord.getDueDate(),
                loanRecord.getPrincipal(),
                loanRecord.getInterest(),
                loanRecord.getTotal(),
                loanRecord.getExtensions().stream().map(toExtension).collect(Collectors.toList()));
                
    }
}
