package io.codelex.loan.microlending.repository.service;

import io.codelex.loan.microlending.InterestFactorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RepositoryInterestFactorService implements InterestFactorService {

    @Override
    public BigDecimal extendLoanInterestFactor(BigDecimal amount, Integer term) {
        
        if (term == 7) {
            return (amount.multiply(new BigDecimal(Math.pow(1 + 0.015, 7))).subtract(amount));
        }
        if (term == 30) {
            return (amount.multiply(new BigDecimal(Math.pow(1 + 0.015, 7))).subtract(amount)).multiply(new BigDecimal(4));
        }
        return null;
    }

}
