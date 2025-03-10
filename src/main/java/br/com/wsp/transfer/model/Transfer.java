package br.com.wsp.transfer.model;

import br.com.wsp.transfer.model.enums.TransferStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cpf;

    @Column(name = "origin_account", nullable = false, length = 10)
    private String originAccount;

    @Column(name = "destination_account", nullable = false, length = 10)
    private String destinationAccount;


    @Column(name = "schedule_date", nullable = false)
    private LocalDateTime scheduleDate;

    @Column(name = "transfer_value")
    private BigDecimal transferValue;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;
    public UUID getId() {
        return id;
    }

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public String getCpf() {
        return cpf;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public BigDecimal getTransferValue() {
        return transferValue;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void setTransferValue(BigDecimal transferValue) {
        this.transferValue = transferValue;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
