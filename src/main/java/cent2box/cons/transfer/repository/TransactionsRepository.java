package cent2box.cons.transfer.repository;

import cent2box.cons.transfer.dto.TransactionBasicDTO;
import cent2box.cons.transfer.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByUserID(Long userId);

    List<Transactions> findTop5ByUserIDOrderByTransactionDateDesc(Long userId);

    @Query("SELECT new cent2box.cons.transfer.dto.TransactionBasicDTO(t.transactionID, t.transactionDate, t.paymentType, t.amount) " +
            "FROM Transactions t WHERE t.userID = :userId ORDER BY t.transactionDate DESC")
    List<TransactionBasicDTO> findTransactionSummaryByUserID(@Param("userId") Long userId);

    List<TransactionBasicDTO> findTop3ByUserIDOrderByTransactionDateDesc(Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId ORDER BY t.date DESC")
    List<TransactionBasicDTO> findByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.cashier.id = :userId")
    List<TransactionBasicDTO> findByCashierId(@Param("userId") Long userId);



}

