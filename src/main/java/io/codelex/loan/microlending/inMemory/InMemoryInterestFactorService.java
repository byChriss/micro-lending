package io.codelex.loan.microlending.inMemory;

import io.codelex.loan.microlending.InterestFactorService;
import org.springframework.stereotype.Component;

@Component
public class InMemoryInterestFactorService implements InterestFactorService {
    @Override
    public Double extendLoanInterestFactor(Long amount, Long term) {
        return getaDouble(amount, term);
    }
    private static Double getaDouble(Long amount, Long term) {
        if (term == 7) {

            return amount * Math.pow(1 + 0.015, 7) - amount;
        }
        if (term == 30) {
            return (amount * Math.pow(1 + 0.015, 7) - amount) * 4;
        }
        return null;
    }
}
