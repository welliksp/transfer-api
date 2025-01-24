package br.com.wsp.transfer.service.impl;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.exception.TransferNotFoundException;
import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.model.enums.TransferStatus;
import br.com.wsp.transfer.repository.TransferRepository;
import br.com.wsp.transfer.service.ITaxCalculatorService;
import br.com.wsp.transfer.service.ITransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService implements ITransferService {

    private final TransferRepository repository;
    private final ITaxCalculatorService taxService;

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public TransferService(TransferRepository repository, ITaxCalculatorService taxService) {
        this.repository = repository;
        this.taxService = taxService;
    }

    @Override
    public Optional<TransferDto> save(TransferDto transferDto) {

        BigDecimal tax = taxService.calculateTax(
                transferDto.getScheduleDate(),
                transferDto.getTransferValue()
        );

        logger.info("CREATE NEW INSTANCE TO TRANSFER");
        Transfer transfer = new Transfer();
        transfer.setCpf(transferDto.getCpf());
        transfer.setOriginAccount(transferDto.getOriginAccount());
        transfer.setDestinationAccount(transferDto.getDestinationAccount());
        transfer.setTransferDate(transferDto.getTransferDate());
        transfer.setScheduleDate(transferDto.getScheduleDate());
        transfer.setTransferValue(transferDto.getTransferValue());
        transfer.setInterestRate(tax);
        transfer.setStatus(TransferStatus.PENDING);

        logger.info("SAVE TRANSFER");
        Transfer transferSaved = repository.save(transfer);
        logger.info("TRANSFER SAVED ID: {}", transferSaved.getId());
        return Optional.of(new TransferDto(transferSaved));
    }

    @Override
    public Optional<TransferDto> findById(UUID id) {

        logger.info("FIND TRANSFER BY ID: {}", id);
        Transfer transfer = repository.findById(id).orElseThrow(() -> new TransferNotFoundException(id.toString()));

        return Optional.of(new TransferDto(transfer));
    }

    @Override
    public Page<TransferDto> findAll(Pageable pageable) {

        logger.info("FIND ALL TRANSFER");
        Page<Transfer> transferPage = repository.findAll(pageable);
        Page<TransferDto> map = transferPage.map(TransferDto::new);
        return map;
    }


    @Override
    public void delete(UUID uuid) {

        logger.info("DELETE TRANSFER BY ID: {}", uuid);
        repository.deleteById(uuid);
    }

}