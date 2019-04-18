package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanExtensionRecordRepository extends JpaRepository<ExtensionRecord, Long> {
    
    @Query("select extention from ExtensionRecord extention" 
    + " where extention.loanId = :loanId")
    List<ExtensionRecord> findAllExtensionsForUser(@Param("loanId") LoanRecord loanId);
}
