package io.codelex.loan.microlending;

import java.math.BigDecimal;

public interface InterestFactorService {
    
    BigDecimal calculateExtensionInterest(BigDecimal principal, Integer days);

    BigDecimal calculate(BigDecimal principal, Integer days);

}
