package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.InterestFactorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "micro-lending", name = "store-type", havingValue = "database")
public class RepositoryInterestFactorService implements InterestFactorService {

    @Override
    public Double extendLoanInterestFactor(Long amount, Long term) {
        return null;
    }
}
