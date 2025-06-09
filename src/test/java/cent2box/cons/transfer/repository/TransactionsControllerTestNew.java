package cent2box.cons.transfer.repository;

import cent2box.cons.transfer.controller.TransactionsController;
import cent2box.cons.transfer.dto.TransactionBasicDTO;
import cent2box.cons.transfer.dto.TransactionDTO;
import cent2box.cons.transfer.model.Transactions;
import cent2box.cons.transfer.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTestNew {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    private TransactionDTO sampleTransaction;
    private TransactionBasicDTO sampleBasic;

    @BeforeEach
    void setup() {
        sampleTransaction = new TransactionDTO(1L, "Purchase", "Digital Wallet", new BigDecimal("10.00"), "EUR", "T001", LocalDateTime.now());
        sampleBasic = new TransactionBasicDTO(1L, LocalDateTime.now(), "Digital Wallet", new BigDecimal("10.00"));
    }

    @Test
    void testCashRestTransfer() throws Exception {
        Mockito.when(walletService.cashRestTransfer(any(), any(), any(), any()))
                .thenReturn("Transfer successful!");

        mockMvc.perform(post("/api/transactions/cash-rest-transfer")
                        .param("consumerId", "1")
                        .param("shopId", "2")
                        .param("amount", "10.00")
                        .param("terminal", "T001"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer successful!"));
    }

    @Test
    void testGetWalletBalance() throws Exception {
        Mockito.when(walletService.getWalletBalance(1L)).thenReturn(new BigDecimal("100.00"));

        mockMvc.perform(get("/api/transactions/wallet/balance")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.00"));
    }

    @Test
    void testGetTransactionHistory() throws Exception {
        Mockito.when(walletService.getTransactionHistory(1L)).thenReturn(List.of(toEntity(sampleTransaction)));

        mockMvc.perform(get("/api/transactions/wallet/history")
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetLastFiveTransactions() throws Exception {
        Mockito.when(walletService.getLastFiveTransactions(1L)).thenReturn(List.of(sampleTransaction));

        mockMvc.perform(get("/api/transactions/last-five")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetTransactionSummary() throws Exception {
        Mockito.when(walletService.getTransactionSummary(1L)).thenReturn(List.of(sampleBasic));

        mockMvc.perform(get("/api/transactions/summary-basic")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetTransaction3Summary() throws Exception {
        Mockito.when(walletService.getTransaction3Summary(1L)).thenReturn(List.of(sampleBasic));

        mockMvc.perform(get("/api/transactions/summary-basic3")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    private Transactions toEntity(TransactionDTO dto) {
        Transactions t = new Transactions();
        t.setTransactionID(dto.getTransactionID());
        t.setTransactionType(dto.getTransactionType());
        t.setPaymentType(dto.getPaymentType());
        t.setAmount(dto.getAmount());
        t.setCurrency(dto.getCurrency());
        t.setTerminal(dto.getTerminal());
        t.setTransactionDate(dto.getTransactionDate());
        return t;
    }
}

