package cent2box.cons.transfer.repository;

import cent2box.cons.transfer.TransferApplication;
import cent2box.cons.transfer.model.DigitalWallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TransferApplication.class) // Specify the correct main class here
public class DigitalWalletRepositoryTest {

    @Autowired
    private DigitalWalletRepository walletRepository;

    @Test
    public void testUpdateBalance() {
        DigitalWallet wallet = walletRepository.findByUserID(2L).orElseThrow();
        wallet.setBalance(wallet.getBalance().add(new BigDecimal("30.00")));
        walletRepository.save(wallet);
        DigitalWallet updatedWallet = walletRepository.findByUserID(2L).orElseThrow();
        assertEquals(new BigDecimal("1282.00"), updatedWallet.getBalance());
    }
}

