package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {

    @Query("select count(loan) > 0 from LoanRecord loan"
            + " where loan.id = :id")
    boolean isLoanPresent(@Param("id") Long id);
    
}
