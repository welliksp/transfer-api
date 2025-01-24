package br.com.wsp.transfer.dto;

import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.model.enums.TransferStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransferDto extends EntityModel<TransferDto> {

    private UUID id;
    @NotNull
    @NotEmpty
    private String cpf;
    @NotNull
    @NotEmpty
    private String originAccount;
    @NotNull
    @NotEmpty
    private String destinationAccount;
    @NotNull
    private LocalDateTime transferDate;
    @NotNull
    private LocalDateTime scheduleDate;
    private BigDecimal transferValue;
    private BigDecimal interestRate;
    private TransferStatus status;

    public TransferDto() {
    }

    public TransferDto(Transfer transfer) {
        this.id = transfer.getId();
        this.cpf = transfer.getCpf();
        this.originAccount = transfer.getOriginAccount();
        this.destinationAccount = transfer.getDestinationAccount();
        this.transferDate = transfer.getTransferDate();
        this.scheduleDate = transfer.getScheduleDate();
        this.transferValue = transfer.getTransferValue();
        this.status = transfer.getStatus();
        this.interestRate = transfer.getInterestRate();

    }

    public UUID getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public BigDecimal getTransferValue() {
        return transferValue;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void setTransferValue(BigDecimal transferValue) {
        this.transferValue = transferValue;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
}