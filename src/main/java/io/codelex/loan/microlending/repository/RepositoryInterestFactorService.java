package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.InterestFactorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RepositoryInterestFactorService implements InterestFactorService {

    @Override
    public BigDecimal extendLoanInterestFactor(Long amount, Long term) {

        if (term == 7) {
            return new BigDecimal( amount * Math.pow(1 + 0.015, 7) - amount);
        }
        if (term == 30) {
            return new BigDecimal((amount * Math.pow(1 + 0.015, 7) - amount) * 4);
        }
        return null;
    }

}
