package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class SameDayTaxCalculator extends AbstractTaxCalculator {
    public SameDayTaxCalculator() {
        super(0, 0);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return transferValue.multiply(BigDecimal.valueOf(0.025)).add(BigDecimal.valueOf(3.00));
    }
}
