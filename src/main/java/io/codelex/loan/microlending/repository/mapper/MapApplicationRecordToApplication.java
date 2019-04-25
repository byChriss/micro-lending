package io.codelex.loan.microlending.repository.mapper;

import io.codelex.loan.microlending.api.Application;
import io.codelex.loan.microlending.repository.model.ApplicationRecord;

import java.util.function.Function;

public class MapApplicationRecordToApplication implements Function<ApplicationRecord, Application> {
    @Override
    public Application apply(ApplicationRecord applicationRecord) {
        return new Application(
                applicationRecord.getId(),
                applicationRecord.getAmount(),
                applicationRecord.getTerm(),
                applicationRecord.getStatus()
        );
    }
}
