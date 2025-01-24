package br.com.wsp.transfer.service;

import br.com.wsp.transfer.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


public interface ITaxCalculatorService {

    public BigDecimal calculateTax(LocalDateTime scheduleDate, BigDecimal transferValue);
}
