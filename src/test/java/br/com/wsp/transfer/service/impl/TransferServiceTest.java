package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.exception.TransferBadRequestException;
import br.com.wsp.transfer.exception.TransferNotFoundException;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.repository.TransferRepository;
import br.com.wsp.transfer.service.ITaxCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    TransferService service;
    @Mock
    ITaxCalculatorService taxService;
    @Mock
    TransferRepository repository;
    @Mock
    Transfer transfer;
    @Mock
    TransferDto transferDto;
    @Mock
    Pageable pageable;

    @Test
    @DisplayName("Create Transfer Should Return Sucess")
    void testCreateTransfer__shouldReturnSucess() {

        doReturn(transfer).when(repository).save(any(Transfer.class));
        doReturn(BigDecimal.TEN).when(taxService).calculateTax(any(LocalDateTime.class), any(BigDecimal.class));
        doReturn(LocalDateTime.now().plusDays(3)).when(transferDto).getScheduleDate();
        doReturn(BigDecimal.TEN).when(transferDto).getTransferValue();

        Optional<TransferDto> result = service.save(transferDto);

        assertNotNull(result);

        verify(repository, times(1)).save(any(Transfer.class));
        verify(taxService, times(1)).calculateTax(any(LocalDateTime.class), any(BigDecimal.class));
    }

    @Test
    @DisplayName("Create Transfer Should Return Exception")
    void testCreateTransfer__shouldReturnException() {

        doThrow(TransferBadRequestException.class).when(repository).save(any(Transfer.class));

        doReturn(BigDecimal.TEN).when(taxService).calculateTax(any(LocalDateTime.class), any(BigDecimal.class));
        doReturn(LocalDateTime.now().plusDays(3)).when(transferDto).getScheduleDate();
        doReturn(BigDecimal.TEN).when(transferDto).getTransferValue();

        assertThrows(TransferBadRequestException.class, () -> service.save(transferDto));


        verify(repository, times(1)).save(any(Transfer.class));
        verify(taxService, times(1)).calculateTax(any(LocalDateTime.class), any(BigDecimal.class));
    }

    @Test
    @DisplayName("Find Transfer By ID Should Return One Transfer")
    void testFindTransferById__shouldReturnSucess() {

        doReturn(Optional.of(transfer)).when(repository).findById(any(UUID.class));

        Optional<TransferDto> result = service.findById(UUID.randomUUID());

        assertNotNull(result);

        verify(repository, times(1)).findById(any(UUID.class));
    }


    @Test
    @DisplayName("Find Transfer By ID Should Return NotFoundException")
    void testFindOneTransfer__shouldReturnSucess() {

        doThrow(TransferNotFoundException.class).when(repository).findById(any(UUID.class));

        assertThrows(TransferNotFoundException.class, () -> service.findById(UUID.randomUUID()));

        verify(repository, times(1)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Find All Transfers Should Return Page")
    void testFindAllTransfers__ShouldReturnPage() {


        Page<Transfer> paged = new PageImpl<>(List.of(transfer));

        doReturn(paged).when(repository).findAll(any(Pageable.class));

        Page<TransferDto> transferPage = service.findAll(pageable);

        assertNotNull(transferPage);

        verify(repository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Delete Transfer By Id Should Return Sucess")
    void testDeleteByIdTransferScheduling__shouldReturnSucess() {

        UUID uuid = UUID.randomUUID();

        doReturn(Optional.of(transfer)).when(repository).findById(any(UUID.class));
        when(transfer.getId()).thenReturn(uuid);

        service.deleteById(uuid);

        verify(repository, times(1)).deleteById(uuid);
    }
}