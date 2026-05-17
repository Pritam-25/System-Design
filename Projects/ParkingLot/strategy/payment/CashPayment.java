package strategy.payment;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing cash payment of amount: " + amount);
        return true; // Simulate successful payment
    }
}
