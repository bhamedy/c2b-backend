package cent2box.cons.transfer.controller;

import cent2box.cons.transfer.dto.TransactionBasicDTO;
import cent2box.cons.transfer.dto.TransactionDTO;
import cent2box.cons.transfer.model.Transactions;
import cent2box.cons.transfer.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private WalletService walletService;

    // Endpoint for performing a cash rest transfer
    @RequestMapping(value = "/cash-rest-transfer", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> cashRestTransfer(
            @RequestParam Long consumerId,
            @RequestParam Long shopId,
            @RequestParam BigDecimal amount,
            @RequestParam String terminal
    ) {
        try {
            String response = walletService.cashRestTransfer(consumerId, shopId, amount, terminal);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // New Endpoint to get the wallet balance of a user
    @GetMapping("/wallet/balance")
    public ResponseEntity<BigDecimal> getWalletBalance(@RequestParam Long userId) {
        try {
            BigDecimal balance = walletService.getWalletBalance(userId);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/wallet/history")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@RequestParam Long customerId) {
        List<Transactions> transactions = walletService.getTransactionHistory(customerId);

        // Convert entity list to DTO list
        List<TransactionDTO> transactionHistory = transactions.stream().map(transaction ->
                new TransactionDTO(
                        transaction.getTransactionID(),
                        transaction.getTransactionType(),
                        transaction.getPaymentType(),
                        transaction.getAmount(),
                        transaction.getCurrency(),
                        transaction.getTerminal(),
                        transaction.getTransactionDate()
                )).toList();

        return ResponseEntity.ok(transactionHistory);
    }

    // Endpoint: Get last 5 transactions
    @GetMapping("/last-five")
    public ResponseEntity<List<TransactionDTO>> getLastFiveTransactions(@RequestParam Long userId) {
        try {
            List<TransactionDTO> lastFiveTransactions = walletService.getLastFiveTransactions(userId);
            return ResponseEntity.ok(lastFiveTransactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // New Endpoint: Get transaction summary
    @GetMapping("/summary-basic")
    public ResponseEntity<List<TransactionBasicDTO>> getTransactionSummary(@RequestParam Long userId) {
        try {
            List<TransactionBasicDTO> transactionSummary = walletService.getTransactionSummary(userId);
            return ResponseEntity.ok(transactionSummary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // New Endpoint: Get transaction summary
    @GetMapping("/summary-basic3")
    public ResponseEntity<List<TransactionBasicDTO>> getTransaction3Summary(@RequestParam Long userId) {
        try {
            List<TransactionBasicDTO> transactionSummary = walletService.getTransaction3Summary(userId);
            return ResponseEntity.ok(transactionSummary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
