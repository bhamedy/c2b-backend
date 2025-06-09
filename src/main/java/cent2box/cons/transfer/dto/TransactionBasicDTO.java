package cent2box.cons.transfer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionBasicDTO {
    private Long transactionID;
    private LocalDateTime transactionDate;
    private String paymentType;
    private BigDecimal amount;

    // Constructor
    public TransactionBasicDTO(Long transactionID, LocalDateTime transactionDate, String paymentType, BigDecimal amount) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
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
}
