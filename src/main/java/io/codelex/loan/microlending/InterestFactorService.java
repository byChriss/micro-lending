package io.codelex.loan.microlending;

import java.math.BigDecimal;

public interface InterestFactorService {

    BigDecimal calculate(BigDecimal principal, Integer days);

    BigDecimal calculateExtensionInterest(BigDecimal principal, Integer days);

}
