package cent2box.cons.transfer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Long transactionID;
    private String transactionType;
    private String paymentType;
    private BigDecimal amount;
    private String currency;
    private String terminal;
    private LocalDateTime transactionDate;

    // Constructor
    public TransactionDTO(Long transactionID, String transactionType, String paymentType, BigDecimal amount,
                          String currency, String terminal, LocalDateTime transactionDate) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.paymentType = paymentType;
        this.amount = amount;
        this.currency = currency;
        this.terminal = terminal;
        this.transactionDate = transactionDate;
    }


    // Getters and Setters
    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
