package io.codelex.loan.microlending;

import java.math.BigDecimal;

public interface InterestFactorService {
    BigDecimal extendLoanInterestFactor(BigDecimal amount, Integer term);

}
