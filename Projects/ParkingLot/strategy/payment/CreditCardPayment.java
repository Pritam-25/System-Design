package strategy.payment;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of amount: " + amount);
        return true; // Simulate successful payment
    }
}
