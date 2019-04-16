package io.codelex.loan.microlending;

import java.math.BigDecimal;

public interface InterestFactorService {
    //todo why double?
    BigDecimal extendLoanInterestFactor(Long amount, Long term);
    
}
