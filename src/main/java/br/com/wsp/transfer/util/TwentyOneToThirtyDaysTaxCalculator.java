package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class TwentyOneToThirtyDaysTaxCalculator extends AbstractTaxCalculator {
    public TwentyOneToThirtyDaysTaxCalculator() {
        super(21, 30);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return transferValue.multiply(BigDecimal.valueOf(0.069));
    }
}