package io.codelex.loan.microlending;

public interface InterestFactorService {
    Double extendLoanInterestFactor(Long amount, Long term);
    
}
