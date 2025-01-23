package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.exception.TransferNotFoundException;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.model.enums.TransferStatus;
import br.com.wsp.transfer.repository.TransferRepository;
import br.com.wsp.transfer.service.ITransferService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService implements ITransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<TransferDto> save(TransferDto transferDto) {

        Transfer transfer = new Transfer();
        transfer.setOriginAccount(transferDto.getOriginAccount());
        transfer.setDestinationAccount(transferDto.getDestinationAccount());
        transfer.setTransferDate(transferDto.getTransferDate());
        transfer.setScheduleDate(transferDto.getScheduleDate());
        transfer.setAmount(transferDto.getAmount());
        transfer.setStatus(TransferStatus.PENDING);

        Transfer transferSaved = repository.save(transfer);

        return Optional.of(new TransferDto(transferSaved));
    }

    @Override
    public Optional<TransferDto> findById(UUID id) {

        Transfer transfer = repository.findById(id).orElseThrow(() -> new TransferNotFoundException(id.toString()));

        return Optional.of(new TransferDto(transfer));
    }
}