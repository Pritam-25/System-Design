package bank;

import java.util.Random;

public class PaytmBankingSystem implements BankingSystem {
    private final Random rand = new Random();

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Paytm] Processing payment of " + amount + "...");
        return rand.nextInt(100) < 80; // 80% success rate
    }
}
