package io.codelex.loan.microlending;

import java.math.BigDecimal;

public interface InterestFactorService {
    BigDecimal extendLoanInterestFactor(Long amount, Long term);

}
