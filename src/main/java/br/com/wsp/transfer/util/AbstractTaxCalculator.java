package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public  abstract class AbstractTaxCalculator implements TaxCalculator{


    protected final long minDays;
    protected final long maxDays;

    protected AbstractTaxCalculator(long minDays, long maxDays) {
        this.minDays = minDays;
        this.maxDays = maxDays;
    }

    @Override
    public boolean supports(long daysToTransfer) {
        return daysToTransfer >= minDays && daysToTransfer <= maxDays;
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return null;
    }
}