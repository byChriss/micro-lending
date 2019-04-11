package io.codelex.loan.microlending.service;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LoanService {
    private LocalTime currentTime = LocalTime.now();
    private LocalTime endTime = LocalTime.parse("07:00:00");
    private final Long maxAmount = 500L;
    private List<Loan> loans = new ArrayList<>();
    private AtomicLong loansID = new AtomicLong();
    private IpService ipService;
    
    public Loan createLoan(LoanRequest request, HttpServletRequest servletRequest){
        if(!ipService.maxAttemptsFromIpReached()){
            if (checkTime() && request.getAmount().equals(maxAmount)) {
                return null;
            }else {
                Loan loan = new Loan(
                        loansID.getAndIncrement(),
                        request.getAmount(),
                        request.getTerm(),
                        true

                );
                loans.add(loan);
                return loan;
            }
          
        }
        return null;
    }
    
    public Loan findById(Long id){
        if(isLoanPresent(id)){
            return loans.stream()
                    .filter(loan -> loan.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
       return null;
    }
    
    
    private boolean isLoanPresent(Long id){
        for (Loan loan :loans) {
            if(loan.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    private boolean checkTime(){
        if(currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(endTime)){
            return true;
        }
        return false;
    }

}
