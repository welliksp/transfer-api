package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.exception.IllegalScheduleDateAndValueException;
import br.com.wsp.transfer.service.ITaxCalculatorService;
import br.com.wsp.transfer.util.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TaxCalculatorService implements ITaxCalculatorService {
    private static final Logger logger = Logger.getLogger(TaxCalculatorService.class.getName());

    private final List<TaxCalculator> taxCalculators = List.of(
            new SameDayTaxCalculator(),
            new OneToTenDaysTaxCalculator(),
            new ElevenToTwentyDaysTaxCalculator(),
            new TwentyOneToThirtyDaysTaxCalculator(),
            new ThirtyOneToFortyDaysTaxCalculator(),
            new FortyOneToFiftyDaysTaxCalculator()
    );


    @Override
    public BigDecimal calculateTax(LocalDateTime scheduleDate, BigDecimal transferValue) throws IllegalArgumentException {

        long daysToTransfer = ChronoUnit.DAYS.between(LocalDateTime.now(), scheduleDate);

        validateInput(scheduleDate, transferValue, daysToTransfer);

        Optional<TaxCalculator> calculator = taxCalculators.stream()
                .filter(c -> c.supports(daysToTransfer))
                .findFirst();

        return calculator
                .map(c -> c.calculate(transferValue))
                .orElseThrow(() -> new IllegalArgumentException("No applicable tax found for " + daysToTransfer + " days."));
    }

    private void validateInput(LocalDateTime scheduleDate, BigDecimal transferValue, Long daysToTransfer) {

        if ( daysToTransfer >= 51 ||scheduleDate.isBefore(LocalDateTime.now()) || transferValue.compareTo(BigDecimal.ZERO) <= 0 ) {

            logger.severe("Invalid transfer date or value.");
            throw new IllegalScheduleDateAndValueException("Transfer date or value is invalid.");
        }
    }
}