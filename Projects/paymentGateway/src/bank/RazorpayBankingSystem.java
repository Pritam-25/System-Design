package bank;

import java.util.Random;

public class RazorpayBankingSystem implements  BankingSystem {
    private final Random rand = new Random();

    public RazorpayBankingSystem(){};

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Razorpay] Processing payment of " + amount + "...");
        return rand.nextInt(100) < 90; // 90% success rate
    }
}
