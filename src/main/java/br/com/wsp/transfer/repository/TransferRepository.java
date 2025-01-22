package br.com.wsp.transfer.repository;

import br.com.wsp.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
