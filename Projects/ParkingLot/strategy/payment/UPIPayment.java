package strategy.payment;

public class UPIPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment of amount: " + amount);
        return true; // Simulate successful payment
    }
}
