package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {


    @Query("select count(loan) > 0 from LoanRecord loan"
            + " where loan.id = :id")
    boolean isLoanPresent(@Param("id") Long id);

    @Modifying
    @Query("update LoanRecord loan Set loan.repaymentDate = loan.repaymentDate + 7"
            + " where loan.id = :id")
    void updateRepaymentDateByWeek(@Param("id") Long id);

    @Modifying
    @Query("update LoanRecord loan Set loan.repaymentDate = loan.repaymentDate + 30"
            + " where loan.id = :id")
    void updateRepaymentDateByMonth(@Param("id") Long id);

    @Query("select loan from LoanRecord  loan"
            + " where loan.id = :id")
    LoanRecord findLoanById(@Param("id") Long id);

    @Query("select loan from LoanRecord loan"
            + " where loan.owner = :owner")
    List<LoanRecord> findLoanWithCurrentUser(@Param("owner") UserRecord owner);

    @Query("select count(loan) > 0 from LoanRecord  loan"
            + " where loan.owner = :owner")
    boolean checkIfCurrentUserHaveLoan(@Param("owner") UserRecord owner);
}
