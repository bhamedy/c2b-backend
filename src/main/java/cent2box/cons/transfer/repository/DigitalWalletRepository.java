package cent2box.cons.transfer.repository;

import cent2box.cons.transfer.model.DigitalWallet;
import cent2box.cons.transfer.model.Transactions;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, Long> {
    Optional<DigitalWallet> findByUserID(Long userID);
}


