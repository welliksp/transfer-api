package br.com.wsp.transfer.service;

import br.com.wsp.transfer.dto.TransferDto;

import java.util.Optional;
import java.util.UUID;

public interface ITransferService {

    Optional<TransferDto> save(TransferDto transferDto);

    Optional<TransferDto> findById(UUID id);
}
