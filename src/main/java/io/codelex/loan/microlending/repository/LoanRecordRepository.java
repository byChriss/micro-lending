package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
    
}
