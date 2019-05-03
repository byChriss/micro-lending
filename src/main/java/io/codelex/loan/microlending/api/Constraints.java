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

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMinTermDays() {
        return minTermDays;
    }

    public void setMinTermDays(Integer minTermDays) {
        this.minTermDays = minTermDays;
    }

    public Integer getMaxTermDays() {
        return maxTermDays;
    }

    public void setMaxTermDays(Integer maxTermDays) {
        this.maxTermDays = maxTermDays;
    }

    public Integer getMinExtensionDays() {
        return minExtensionDays;
    }

    public void setMinExtensionDays(Integer minExtensionDays) {
        this.minExtensionDays = minExtensionDays;
    }

    public Integer getMaxExtensionDays() {
        return maxExtensionDays;
    }

    public void setMaxExtensionDays(Integer maxExtensionDays) {
        this.maxExtensionDays = maxExtensionDays;
    }
}
