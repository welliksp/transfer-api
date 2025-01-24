package br.com.wsp.transfer.service;

import br.com.wsp.transfer.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITransferService {

    Optional<TransferDto> save(TransferDto transferDto);

    Optional<TransferDto> findById(UUID id);

    List<TransferDto> findAll();

    void deleteById(UUID uuid);
}
