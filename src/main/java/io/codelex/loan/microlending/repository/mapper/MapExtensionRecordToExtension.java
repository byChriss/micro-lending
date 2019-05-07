package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;

import java.util.List;
import java.util.function.Function;

public class MapExtensionRecordToExtension implements Function<ExtensionRecord, LoanExtension> {

    @Override
    public LoanExtension apply(ExtensionRecord extensionRecord) {
        return new LoanExtension(
                extensionRecord.getCreated(),
                extensionRecord.getDays(),
                extensionRecord.getInterest()
        );
    }

}
