package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanExtensionRecordRepository extends JpaRepository<ExtensionRecord, Long> {
}
