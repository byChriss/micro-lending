package io.codelex.loan.microlending.inMemory;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.inMemory.service.LoanService;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryLoanService {
    private LocalTime currentTime =  LocalTime.now();
    private LocalTime endTime = LocalTime.parse("07:00:00");
    private final Long maxAmount = 500L;
    private LoanService loanService;
    private AtomicLong loanSequence = new AtomicLong(1L);

    public InMemoryLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

   public Loan createLoan(LoanRequest request){
        if(checkTime() && request.getAmount().equals(maxAmount)){
            Loan loan = new Loan(
                    loanSequence.getAndIncrement(),
                    request.getAmount(),
                    request.getTerm(),
                    true
            );
            loanService.addLoan(loan);
            return loan;
        }
        return null;
    }


    private boolean checkTime(){
        if(currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(endTime)){
            return true;
        }
        return false;
    }










/*    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }*/

}
