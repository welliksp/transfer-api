package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class OneToTenDaysTaxCalculator extends AbstractTaxCalculator {
    public OneToTenDaysTaxCalculator() {
        super(1, 10);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return BigDecimal.valueOf(12.00);
    }
}