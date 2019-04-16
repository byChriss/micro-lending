package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpServletRequest;


class RepositoryLoanServiceTest {
    private LoanRecordRepository loanRecordRepository = Mockito.mock(LoanRecordRepository.class);
    private UserRecordRepository userRecordRepository = Mockito.mock(UserRecordRepository.class);
    private LoanExtensionRecordRepository extensionRecordRepository = Mockito.mock(LoanExtensionRecordRepository.class);
    private RepositoryLoanService service = new RepositoryLoanService(loanRecordRepository, extensionRecordRepository, userRecordRepository);
    HttpServletRequest servletRequest = Mockito.mock(HttpServletRequest.class);

    @Test
    void should_be_able_creat_loan() {
        LoanRequest request = new LoanRequest(
                200L,
                30L
        );


        Mockito.when(loanRecordRepository.save(Mockito.any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        Loan record = service.createLoan("smt@one.lv", request, servletRequest);

        Assert.assertEquals(record.getAmount(), request.getAmount());
        Assert.assertEquals(record.getTerm(), request.getTerm());
    }


/*    UserRecord userRecord = new UserRecord();
        userRecord.setId(1L);
        userRecord.setUsername("User");
        userRecord.setPassword("12345");
        userRecord.setFirstName("Krists");
        userRecord.setLastName("Abols");
        userRecord.setEmail("smt@one.lv");

    LoanRecord loanRecord = new LoanRecord();
        loanRecord.setId(1L);
        loanRecord.setAmount(100L);
        loanRecord.setTerm(30L);
        loanRecord.setApprovalDate(LocalDate.now());
        loanRecord.setRepaymentDate(LocalDate.now().plusDays(30));
        loanRecord.setExtendAmount(BigDecimal.valueOf(50));
        loanRecord.setStatus(true);
        loanRecord.setOwner(userRecord);*/
}
