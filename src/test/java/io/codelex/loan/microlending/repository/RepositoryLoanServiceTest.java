package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.mapper.MapExtensionRecordToExtension;
import io.codelex.loan.microlending.repository.mapper.MapLoanRecordToLoan;
import io.codelex.loan.microlending.repository.mapper.MapUserRecordToUser;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

class RepositoryLoanServiceTest {

    private LoanRecordRepository loanRecordRepository = Mockito.mock(LoanRecordRepository.class);
    private UserRecordRepository userRecordRepository = Mockito.mock(UserRecordRepository.class);
    private LoanExtensionRecordRepository extensionRecordRepository = Mockito.mock(LoanExtensionRecordRepository.class);
    private RepositoryLoanService service = new RepositoryLoanService(loanRecordRepository, extensionRecordRepository, userRecordRepository);
    private HttpServletRequest servletRequest = Mockito.mock(HttpServletRequest.class);
    private RepositoryIpService ipService = Mockito.mock(RepositoryIpService.class);
    private MapLoanRecordToLoan toLoan = Mockito.mock(MapLoanRecordToLoan.class);
    private MapUserRecordToUser toUser = Mockito.mock(MapUserRecordToUser.class);
    private MapExtensionRecordToExtension toExtension = Mockito.mock(MapExtensionRecordToExtension.class);
    private LoanRecord loanRecord = createLoanRecord();
    private UserRecord userRecord = createUserRecord();
    private ExtensionRecord extensionRecord = createExtensionRecord();
    private User user = createUser();
    private Loan loan = createLoan();
    private LoanRequest loanRequest = createLoanRequest();

    @Test
    void should_be_able_creat_loan() {

        //given
        Loan record = createLoan();
        User user = createUser();
        //when
        Mockito.when(loanRecordRepository.save(any()))
                .thenReturn(loanRecord);
        Mockito.when(toLoan.apply(any()))
                .thenReturn(record);


        Loan result = service.createLoan("smt@gmail.com", loanRequest, servletRequest);
        result.setOwner(user);

        //then
        Assert.assertEquals(record.getOwner().getEmail(), result.getOwner().getEmail());
        Assert.assertEquals(record.getAmount(), result.getAmount());
    }

    @Test
    void  should_be_able_find_by_id_and_extend_loan(){
        //given
         BigDecimal extendAmount = new BigDecimal(20);
        Loan loan = createLoan();
        Loan loan1 = new Loan(
                2L,
                300L,
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                extendAmount,
                user,
                true
        );




        LoanExtension extension = createExtension();
        //when
        Mockito.when(loanRecordRepository.isLoanPresent(any()))
                .thenReturn(true);

        Mockito.when(loanRecordRepository.findLoanById(any()))
                .thenReturn(loanRecord);

        Mockito.when(loanRecordRepository.save(any()))
                .thenReturn(loanRecord);
        Mockito.when(toLoan.apply(any()))
                .thenReturn(loan);


        Mockito.when(extensionRecordRepository.save(any()))
                .thenReturn(extensionRecord);
        Mockito.when(toExtension.apply(any()))
                .thenReturn(extension);


        Loan result = service.findByIdAndExtend(1L, 7L);

        Assert.assertNotEquals(loan1,result);


    }

    @Test
    void should_be_able_to_create_loan_extension() {
        //given
        LoanExtension extension = createExtension();
        //when
        Mockito.when(extensionRecordRepository.save(any()))
                .thenReturn(extensionRecord);
        Mockito.when(toExtension.apply(any()))
                .thenReturn(extension);

        LoanExtension loanExtension = service.createLoanExtension(1L, 7L);
        loanExtension.getLoanId().setId(extension.getId());
        //then
        Assert.assertEquals(extension.getExtensionDays(), loanExtension.getExtensionDays());
    }


    private LoanRequest createLoanRequest() {
        return new LoanRequest(
                200L,
                30L
        );
    }

    private LoanRecord createLoanRecord() {
        BigDecimal extendAmount = new BigDecimal(30);
        return new LoanRecord(
                300L,
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                extendAmount,
                true,
                createUserRecord()

        );
    }

    private UserRecord createUserRecord() {
        return new UserRecord(
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"

        );
    }

    private Loan createLoan() {
        BigDecimal extendAmount = new BigDecimal(20);
        return new Loan(
                1L,
                300L,
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                extendAmount,
                user,
                true
        );
    }

    private User createUser() {
        return new User(
                1L,
                "User",
                "12345",
                "Krists",
                "Abols",
                "smt@gmail.com"
        );
    }

    private LoanExtension createExtension() {
        return new LoanExtension(
                1L,
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                loan,
                true
        );
    }

    private ExtensionRecord createExtensionRecord() {
        return new ExtensionRecord(
                7L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                loanRecord,
                true
        );
    }


}
