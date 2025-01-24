package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.exception.IllegalScheduleDateAndValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorServiceTest {

    @InjectMocks
    TaxCalculatorService taxCalculatorService;

    @Test
    void shouldCalculateTaxForTenDaysTransfer() {
        LocalDateTime scheduleDate = LocalDateTime.now().plusDays(10);
        BigDecimal transferValue = BigDecimal.valueOf(5000);

        BigDecimal tax = taxCalculatorService.calculateTax(scheduleDate, transferValue);

        assertNotNull(tax);
        assertTrue(tax.compareTo(BigDecimal.ZERO) > 0, "Tax should be greater than zero");
    }

    @Test
    void shouldThrowExceptionForInvalidDate() {
        LocalDateTime scheduleDate = LocalDateTime.now().minusDays(1); // Data passada
        BigDecimal transferValue = BigDecimal.valueOf(1000);

        Exception exception = assertThrows(IllegalScheduleDateAndValueException.class, () -> {
            taxCalculatorService.calculateTax(scheduleDate, transferValue);
        });

        assertEquals("Transfer date or value is invalid.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNegativeTransferValue() {
        LocalDateTime scheduleDate = LocalDateTime.now().plusDays(5);
        BigDecimal transferValue = BigDecimal.valueOf(-1000); // Valor negativo

        Exception exception = assertThrows(IllegalScheduleDateAndValueException.class, () -> {
            taxCalculatorService.calculateTax(scheduleDate, transferValue);
        });

        assertEquals("Transfer date or value is invalid.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoTaxCalculatorSupportsDays() {
        LocalDateTime scheduleDate = LocalDateTime.now().plusDays(100);
        BigDecimal transferValue = BigDecimal.valueOf(1000);

        assertThrows(IllegalScheduleDateAndValueException.class, () -> {
            taxCalculatorService.calculateTax(scheduleDate, transferValue);
        });

    }
}