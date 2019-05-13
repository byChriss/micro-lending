package io.codelex.loan.microlending.repository.service;

import io.codelex.loan.microlending.InterestFactorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;
import static java.math.RoundingMode.HALF_DOWN;

@Component
public class RepositoryInterestFactorService implements InterestFactorService {
    
    private static final BigDecimal INTEREST_RATE_PER_MONTH = new BigDecimal("0.1");
    private static final BigDecimal EXTENSION_INTEREST_COEFF = new BigDecimal("0.1");
    private static final int DAYS_IN_MONTH = 30;

    @Override
    public BigDecimal calculate(BigDecimal principal, Integer days) {
        var monthlyInterest = principal.setScale(3, HALF_DOWN).multiply(INTEREST_RATE_PER_MONTH);
        return monthlyInterest
                .divide(new BigDecimal(DAYS_IN_MONTH).setScale(3, HALF_DOWN), DOWN)
                .multiply(new BigDecimal(days))
                .setScale(2, HALF_DOWN);
    }
    
    @Override
    public BigDecimal calculateExtensionInterest(BigDecimal principal, Integer days) {
        var interest = calculate(principal, days);
        return interest.add(interest.multiply(EXTENSION_INTEREST_COEFF)).setScale(2, HALF_DOWN);
    }
}
