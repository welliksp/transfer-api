package br.com.wsp.transfer.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transfer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
