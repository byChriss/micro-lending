package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRecordRepositoryTest {

    @Autowired
    UserRecordRepository userRecordRepository;
    @Autowired
    LoanRecordRepository loanRecordRepository;
    @Autowired
    LoanExtensionRecordRepository extensionRecordRepository;
    private UserRecord userRecord = createUser();
    

    @Test
    public void should_not_be_able_find_loan_be_id_if_no_match(){
        //given
        LoanRecord record = createLoan();
        //when
        LoanRecord loanRecords = loanRecordRepository.findLoanById(2L);
        //then
        Assertions.assertNotEquals(record, loanRecords);
    }
    
    @Test
    public void  should_find_loan_when_loan_present(){
        //given
        LoanRecord record = createLoan();
        //when
        LoanRecord loanRecord = loanRecordRepository.findLoanById(1L);
        //then
        Assertions.assertEquals(record,loanRecord);
        
    }
    

    
    private UserRecord createUser(){
        UserRecord userRecord = new UserRecord();
        userRecord.setId(1L);
        userRecord.setUsername("Krists");
        userRecord.setPassword("kaskas");
        userRecord.setFirstName("Janis");
        userRecord.setLastName("abols");
        userRecord.setEmail("smt@one.lv");
        return userRecord;
    }
    
    private LoanRecord createLoan(){
        LoanRecord record = new LoanRecord();
        record.setId(1L);
        record.setAmount(200L);
        record.setTerm(30L);
        record.setApprovalDate(LocalDate.now());
        record.setRepaymentDate(LocalDate.now().plusDays(30));
        record.setStatus(true);
        record.setOwner(userRecord);
        return record;
    }
}
