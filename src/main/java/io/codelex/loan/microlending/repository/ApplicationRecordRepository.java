package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRecordRepository extends JpaRepository<ApplicationRecord, Long> {
}
