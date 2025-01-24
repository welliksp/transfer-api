package br.com.wsp.transfer.util;

import java.math.BigDecimal;

public interface TaxCalculator {


    boolean supports(long daysToTransfer);
    BigDecimal calculate(BigDecimal transferValue);
}
