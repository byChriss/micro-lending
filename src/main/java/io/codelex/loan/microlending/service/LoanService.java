package io.codelex.loan.microlending.service;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class LoanService {
    private LocalTime currentTime = LocalTime.now();
    private LocalDate currentDate = LocalDate.now();
    private LocalTime endTime = LocalTime.parse("07:00:00");
    private final Long maxAmount = 500L;
    private List<Loan> loans = new ArrayList<>();
    private AtomicLong loansID = new AtomicLong();
    private IpService ipService = new IpService();
    private InterestFactorService factorService = new InterestFactorService();
    

    public Loan createLoan(LoanRequest request, HttpServletRequest servletRequest) {
        if (!ipService.maxAttemptsFromIpReached()) {
            if (checkTime() && request.getAmount().equals(maxAmount)) {
                return null;
            } else {
                Loan loan = new Loan(
                        loansID.getAndIncrement(),
                        request.getAmount(),
                        request.getTerm(),
                        currentDate,
                        LocalDate.now().plusDays(request.getTerm()),
                        factorService.extendLoanInterestFactor(request.getAmount(), request.getTerm()),
                        true
                );
                ipService.addIp(servletRequest);
                loans.add(loan);
                return loan;
            }
        }
        return null;
    }

    public Loan findByIdAndExtend(Long id, Long days) {
        if (isLoanPresent(id)) {
            for (Loan loan : loans) {
                if (loan.getId().equals(id)) {
                    loan.setRepaymentsDate(loan.getRepaymentsDate().plusDays(days));
                    loan.setExtendAmount(factorService.extendLoanInterestFactor(loan.getAmount(),days));
                    return loan;
                }

            }
        }
        return null;
    }
    
    
    private boolean isLoanPresent(Long id) {
        for (Loan loan : loans) {
            if (loan.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime() {
        if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(endTime)) {
            return true;
        }
        return false;
    }

 
}
