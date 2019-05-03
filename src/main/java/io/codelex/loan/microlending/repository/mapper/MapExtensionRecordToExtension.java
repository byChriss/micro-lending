package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;

import java.util.List;
import java.util.function.Function;

public class MapExtensionRecordToExtension implements Function<ExtensionRecord, LoanExtension> {
    private MapLoanRecordToLoan toLoan = new MapLoanRecordToLoan();

    @Override
    public LoanExtension apply(ExtensionRecord extensionRecord) {
        return new LoanExtension(
                extensionRecord.getId(),
                extensionRecord.getCreated(),
                extensionRecord.getDays(),
                extensionRecord.getInterest(),
                toLoan.apply(extensionRecord.getLoanId())

        );
    }

}
