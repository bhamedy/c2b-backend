package cent2box.cons.transfer.service;

import cent2box.cons.transfer.dto.TransactionBasicDTO;
import cent2box.cons.transfer.dto.TransactionDTO;
import cent2box.cons.transfer.model.DigitalWallet;
import cent2box.cons.transfer.model.Transactions;
import cent2box.cons.transfer.repository.DigitalWalletRepository;
import cent2box.cons.transfer.repository.TransactionsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class WalletService {

    @Autowired
    private DigitalWalletRepository walletRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private EntityManager entityManager;

    public String cashRestTransfer(Long consumerId, Long shopId, BigDecimal amount, String terminal) {
        // Step 1: Fetch consumer wallet
        DigitalWallet consumerWallet = walletRepository.findByUserID(consumerId)
                .orElseThrow(() -> new WalletNotFoundException("Consumer wallet not found for User ID: " + consumerId));

        // Step 2: Update consumer wallet balance
        consumerWallet.setBalance(consumerWallet.getBalance().add(amount));
        walletRepository.save(consumerWallet); // Save updated balance

        // Step 3: Record transaction
        Transactions transaction = new Transactions();
        transaction.setUserID(consumerId);
        transaction.setShopID(shopId);
        transaction.setWalletID(consumerWallet.getWalletID());
        transaction.setTerminal(terminal);
        transaction.setTransactionType("Cash Rest Transfer");
        transaction.setPaymentType("Digital Wallet");
        transaction.setAmount(amount);
        transaction.setCurrency("EUR");

        transactionsRepository.save(transaction); // Save the transaction

        // Log success
        System.out.println("Transaction recorded successfully: " + transaction);

        // Return success message
        return "Transfer successful!";
    }
    public BigDecimal getWalletBalance(Long userId) {
        return walletRepository.findByUserID(userId)
                .map(DigitalWallet::getBalance)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user ID: " + userId));
    }

    public List<Transactions> getTransactionHistory(Long customerId) {
        return transactionsRepository.findByUserID(customerId);
    }

    // Fetch last 5 transactions
    public List<TransactionDTO> getLastFiveTransactions(Long userId) {
        List<Transactions> transactions = transactionsRepository.findTop5ByUserIDOrderByTransactionDateDesc(userId);

        return transactions.stream().map(transaction ->
                new TransactionDTO(
                        transaction.getTransactionID(),
                        transaction.getTransactionType(),
                        transaction.getPaymentType(),
                        transaction.getAmount(),
                        transaction.getCurrency(),
                        transaction.getTerminal(),
                        transaction.getTransactionDate()
                )).toList();
    }

    // Fetch last 5 transactions
    public List<TransactionBasicDTO> getLastThreeTransactions(Long userId) {
        List<TransactionBasicDTO> transactions = transactionsRepository.findTop3ByUserIDOrderByTransactionDateDesc(userId);

        return transactions.stream().map(transaction ->
                new TransactionBasicDTO(
                        transaction.getTransactionID(),
                        transaction.getTransactionDate(),
                        transaction.getPaymentType(),
                        transaction.getAmount()

                )).toList();
    }

    // Custom exception for wallet-related errors
    public static class WalletNotFoundException extends RuntimeException {
        public WalletNotFoundException(String message) {
            super(message);
        }
    }

    // Fetch basic transaction details
    public List<TransactionBasicDTO> getTransactionSummary(Long userId) {
        return transactionsRepository.findTransactionSummaryByUserID(userId);
    }

    // Fetch basic transaction details
    public List<TransactionBasicDTO> getTransaction3Summary(Long userId) {
        return transactionsRepository.findTop3ByUserIDOrderByTransactionDateDesc(userId);
    }
}
