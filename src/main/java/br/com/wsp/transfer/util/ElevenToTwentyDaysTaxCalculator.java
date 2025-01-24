package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public class ElevenToTwentyDaysTaxCalculator extends AbstractTaxCalculator {
    public ElevenToTwentyDaysTaxCalculator() {
        super(11, 20);
    }

    @Override
    public BigDecimal calculate(BigDecimal transferValue) {
        return transferValue.multiply(BigDecimal.valueOf(0.082));
    }
}