package br.com.wsp.transfer.repository;

import br.com.wsp.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
