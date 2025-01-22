package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.repository.TransferRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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

        doReturn(transfer).when(repository).save(any(Transfer.class));

        TransferDto result = service.save(transferDto);

        assertNotNull(result);
    }
}