package br.com.wsp.transfer.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TransferDto(@NotNull String originAccount, @NotNull String destinationAccount,
                          @NotNull String transferDate, @NotNull LocalDateTime scheduleDate, Float amount) {
}
