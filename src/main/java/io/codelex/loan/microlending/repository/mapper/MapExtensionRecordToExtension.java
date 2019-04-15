package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;

import java.util.function.Function;

public class MapExtensionRecordToExtension implements Function<ExtensionRecord, LoanExtension> {
    private MapLoanRecordToLoan toLoan = new MapLoanRecordToLoan();

    @Override
    public LoanExtension apply(ExtensionRecord extensionRecord) {
        return new LoanExtension(
                extensionRecord.getId(),
                extensionRecord.getExtendDays(),
                extensionRecord.getExtensionDate(),
                extensionRecord.getPaybackDate(),
                toLoan.apply(extensionRecord.getLoanId()),
                extensionRecord.isStatus()
        );
    }
}
