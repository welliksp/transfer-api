package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.model.enums.TransferStatus;
import br.com.wsp.transfer.repository.TransferRepository;
import br.com.wsp.transfer.service.ITransferService;
import org.springframework.stereotype.Service;

@Service
public class TransferService implements ITransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }


    @Override
    public TransferDto save(TransferDto transferDto) {

        Transfer transfer = new Transfer();
        transfer.setOriginAccount(transferDto.getOriginAccount());
        transfer.setDestinationAccount(transferDto.getDestinationAccount());
        transfer.setTransferDate(transferDto.getTransferDate());
        transfer.setScheduleDate(transferDto.getScheduleDate());
        transfer.setAmount(transferDto.getAmount());
        transfer.setStatus(TransferStatus.PENDING);

        Transfer transferSaved = repository.save(transfer);

        return new TransferDto(transferSaved);
    }
}