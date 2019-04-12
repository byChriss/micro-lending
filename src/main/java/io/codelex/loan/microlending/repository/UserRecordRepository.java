package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
    
}
