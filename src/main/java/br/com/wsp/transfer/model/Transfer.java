package br.com.wsp.transfer.model;

import br.com.wsp.transfer.model.enums.TransferStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "origin_account", nullable = false, length = 10)
    private String originAccount;

    @Column(name = "destination_account", nullable = false, length = 10)
    private String destinationAccount;

    @Column(name = "transfer_date", nullable = false)
    private LocalDateTime transferDate;

    @Column(name = "schedule_date", nullable = false)
    private LocalDateTime scheduleDate;

    private Float amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

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
