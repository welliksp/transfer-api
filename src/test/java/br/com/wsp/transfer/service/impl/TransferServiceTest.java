package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.exception.TransferNotFoundException;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.repository.TransferRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    TransferService service;
    @Mock
    TransferRepository repository;
    @Mock
    Transfer transfer;
    @Mock
    TransferDto transferDto;

    @Test
    @DisplayName("Create Transfer Should Return Sucess")
    void testCreateTransfer__shouldReturnSucess() {

        doReturn(Optional.of(transfer)).when(repository).save(any(Transfer.class));

        Optional<TransferDto> result = service.save(transferDto);

        assertNotNull(result.get());
    }

    @Test
    @DisplayName("Find Transfer By ID Should Return One Transfer")
    void testFindTransferById__shouldReturnSucess() {

        doReturn(Optional.of(transfer)).when(repository).findById(any(UUID.class));

        Optional<TransferDto> result = service.findById(UUID.randomUUID());

        assertNotNull(result.get());
    }


    @Test
    @DisplayName("Find Transfer By ID Should Return NotFoundException")
    void testFindOneTransfer__shouldReturnSucess() {

        doThrow(TransferNotFoundException.class).when(repository).findById(any(UUID.class));

        assertThrows(TransferNotFoundException.class, () -> service.findById(UUID.randomUUID()));
    }
}