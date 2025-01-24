package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class ThirtyOneToFortyDaysTaxCalculator extends AbstractTaxCalculator {
    public ThirtyOneToFortyDaysTaxCalculator() {
        super(31, 40);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return transferValue.multiply(BigDecimal.valueOf(0.047));
    }
}