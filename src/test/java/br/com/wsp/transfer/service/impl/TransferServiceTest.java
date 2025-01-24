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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    TransferRepository repository;
    @Mock
    Transfer transfer;
    @Mock
    TransferDto transferDto;
    @Mock
    Pageable pageable;
    @Mock
    Page<Transfer> page;

    @Test
    @DisplayName("Create Transfer Should Return Sucess")
    void testCreateTransfer__shouldReturnSucess() {

        doReturn(transfer).when(repository).save(any(Transfer.class));

        Optional<TransferDto> result = service.save(transferDto);

        assertNotNull(result);

        verify(repository, times(1)).save(any(Transfer.class));
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
    void testDeleteTransferScheduling__shouldReturnSucess() {

        UUID uuid = UUID.randomUUID();

        service.delete(uuid);

        verify(repository, times(1)).deleteById(any(UUID.class));
    }
}