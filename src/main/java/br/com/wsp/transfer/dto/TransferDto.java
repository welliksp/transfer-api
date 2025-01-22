package br.com.wsp.transfer.dto;

import br.com.wsp.transfer.model.Transfer;
import br.com.wsp.transfer.model.enums.TransferStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TransferDto {

    private Long id;
    @NotNull
    private String originAccount;
    @NotNull
    private String destinationAccount;
    @NotNull
    private LocalDateTime transferDate;
    @NotNull
    private LocalDateTime scheduleDate;
    private Float amount;
    private TransferStatus status;

    public TransferDto() {
    }

    public TransferDto(Transfer transfer) {
        this.originAccount = transfer.getOriginAccount();
        this.destinationAccount = transfer.getDestinationAccount();
        this.transferDate = transfer.getTransferDate();
        this.scheduleDate = transfer.getScheduleDate();
        this.amount = transfer.getAmount();
        this.status = transfer.getStatus();
    }

    public Long getId() {
        return id;
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

    public Float getAmount() {
        return amount;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
}