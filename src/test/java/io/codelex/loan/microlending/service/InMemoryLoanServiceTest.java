package io.codelex.loan.microlending.service;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.inMemory.InMemoryLoanService;
import io.codelex.loan.microlending.inMemory.service.LoanService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;



public class InMemoryLoanServiceTest {
    LoanService loanService = Mockito.mock(LoanService.class);
    Loan loan = createLoan();
    LoanRequest loanRequest = createRequest();
    InMemoryLoanService service = new InMemoryLoanService(loanService);

    @Test
    public void should_be_able_get_loan(){
        //given
        LoanRequest request = createRequest();
        //when
        Loan loan = service.createLoan(request);
        //then
        assertLoanCreatedProperly(request, loan);
/*
       Mockito.when(loanService.addLoan(any()))
               .thenReturn(loan);
       Loan result = service.createLoan(loanRequest);

       Assertions.assertEquals(loan, result);*/
    }

    private void assertLoanCreatedProperly(LoanRequest request, Loan result){
        Assertions.assertEquals(request.getAmount(), request.getAmount());
        Assertions.assertEquals(request.getTerm(), request.getTerm());
    }


    private Loan createLoan(){
        return new Loan(
                4L,
                500L,
                25L,
                true
        );
    }

    private LoanRequest createRequest(){
        return new LoanRequest(
                500L,
                25L
        );
    }

}