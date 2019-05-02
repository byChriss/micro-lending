package io.codelex.loan.microlending.api;

import java.math.BigDecimal;

public class Constraints {
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTermDays;
    private Integer maxTermDays;
    private Integer minExtensionDays;
    private Integer maxExtensionDays;

    
    public Constraints(BigDecimal minAmount, BigDecimal maxAmount, Integer minTermDays, Integer maxTermDays, Integer minExtensionDays, Integer maxExtensionDays) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.minTermDays = minTermDays;
        this.maxTermDays = maxTermDays;
        this.minExtensionDays = minExtensionDays;
        this.maxExtensionDays = maxExtensionDays;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public Integer getMinTermDays() {
        return minTermDays;
    }

    public Integer getMaxTermDays() {
        return maxTermDays;
    }

    public Integer getMinExtensionDays() {
        return minExtensionDays;
    }

    public Integer getMaxExtensionDays() {
        return maxExtensionDays;
    }

    public Constraints setConstraints(){
        return new Constraints(
                new BigDecimal (100),
                new BigDecimal(500),
                7,
                30,
                7,
                30
        );
    }
}
