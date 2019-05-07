package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LoanRecordRepository extends JpaRepository<LoanRecord, String> {


    @Query("select count(loan) > 0 from LoanRecord loan"
            + " where loan.id = :id")
    boolean isLoanPresent(@Param("id") String id);

    @Modifying
    @Query("update LoanRecord loan Set loan.dueDate = loan.dueDate + 7"
            + " where loan.id = :id")
    void updateRepaymentDateByWeek(@Param("id") String id);

    @Modifying
    @Query("update LoanRecord loan Set loan.dueDate = loan.dueDate + 30"
            + " where loan.id = :id")
    void updateRepaymentDateByMonth(@Param("id") String id);

    @Query("select loan from LoanRecord  loan"
            + " where loan.id = :id")
    LoanRecord findLoanById(@Param("id") String id);

    @Query("select loan from LoanRecord loan"
            + " where loan.owner = :owner")
    List<LoanRecord> findLoanWithCurrentUser(@Param("owner") UserRecord owner);

    @Query("select count(loan) > 0 from LoanRecord  loan"
            + " where loan.owner = :owner")
    boolean checkIfCurrentUserHaveLoan(@Param("owner") UserRecord owner);
}
