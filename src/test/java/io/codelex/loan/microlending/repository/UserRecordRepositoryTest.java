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
public class UserRecordRepositoryTest {

    @Autowired
    UserRecordRepository userRecordRepository;
    @Autowired
    LoanRecordRepository loanRecordRepository;
    @Autowired
    LoanExtensionRecordRepository extensionRecordRepository;

    private UserRecord userRecord = createUserRecord();


    @Test
    public void should_return_true_if_user_record_present() {
        //given
        userRecord = userRecordRepository.save(userRecord);
        //when
        boolean isUserPresent = userRecordRepository.isUserPresent(userRecord.getUsername());
        //then
        Assertions.assertTrue(isUserPresent);

    }
    @Test
    public void should_return_false_if_user_record_is_not_present() {
        //given
        userRecord = userRecordRepository.save(userRecord);
        //when
        boolean isUserPresent = userRecordRepository.isUserPresent("Nothing");
        //then
        Assertions.assertFalse(isUserPresent);

    }


    @Test
    public void should_find_user_record_by_email() {
        //given
        userRecord = userRecordRepository.save(userRecord);
        //when
        UserRecord user = userRecordRepository.finByEmail(userRecord.getEmail());
        //then
        Assertions.assertEquals(userRecord, user);
    }
    @Test
    public void should_not_find_user_record_by_email_if_dont_exist() {
        //given
        userRecord = userRecordRepository.save(userRecord);
        //when
        UserRecord user = userRecordRepository.finByEmail("nothing@gmail.com");
        //then
        Assertions.assertNotEquals(userRecord, user);
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
