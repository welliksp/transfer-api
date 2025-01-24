package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class FortyOneToFiftyDaysTaxCalculator extends AbstractTaxCalculator {
    public FortyOneToFiftyDaysTaxCalculator() {
        super(41, 50);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return transferValue.multiply(BigDecimal.valueOf(0.017));
    }
}