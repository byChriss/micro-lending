/*
package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.math.BigDecimal;
import java.time.LocalDate;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRecordRepositoryTest {

    @Autowired
    UserRecordRepository userRecordRepository;
    @Autowired
    LoanRecordRepository loanRecordRepository;
    @Autowired
    LoanExtensionRecordRepository extensionRecordRepository;

    private UserRecord userRecord = createUserRecord();
    private LoanRecord loanRecord = createLoanRecord();

    @Test
    public void should_not_be_able_find_loan_be_id_if_no_match() {
        //given
        LoanRecord record = createLoanRecord();
        //when
        LoanRecord loanRecords = loanRecordRepository.findLoanById(2L);
        //then
        Assertions.assertNotEquals(record, loanRecords);
    }

    @Test
    public void should_find_loan_by_id() {
        //given
        loanRecord = loanRecordRepository.save(loanRecord);
        userRecord = userRecordRepository.save(userRecord);
        //when
        LoanRecord loanRecords = loanRecordRepository.findLoanById(loanRecord.getId());
        //then
        Assertions.assertEquals(loanRecords, loanRecord);

    }

    @Test
    public void should_return_true_if_loan_is_present() {
        //given
        loanRecord = loanRecordRepository.save(loanRecord);
        userRecord = userRecordRepository.save(userRecord);
        //when
        boolean isLoanPresent = loanRecordRepository.isLoanPresent(loanRecord.getId());
        //then
        Assertions.assertTrue(isLoanPresent);
    }


    private LoanRecord createLoanRecord() {
        BigDecimal extendAmount = new BigDecimal(30);
        LoanRecord loanRecord = new LoanRecord(
                300L,
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                extendAmount,
                true,
                userRecord

        );
        return loanRecord;
    }

    private UserRecord createUserRecord() {
        UserRecord userRecord = new UserRecord(
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"

        );

        return userRecord;
    }
}
*/
