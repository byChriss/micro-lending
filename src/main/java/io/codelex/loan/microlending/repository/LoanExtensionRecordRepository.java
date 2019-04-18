package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanExtensionRecordRepository extends JpaRepository<ExtensionRecord, Long> {
    
    @Query("select extention from ExtensionRecord extention" 
    + " where extention.loanId = :email")
    List<ExtensionRecord> findAllExtensionsForUser(@Param("email") String email);
}
