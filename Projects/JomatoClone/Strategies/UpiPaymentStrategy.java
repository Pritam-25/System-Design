package Projects.ZomatoClone.strategies;

public class UpiPaymentStrategy implements IPaymentStrategy {
    @Override
    public void pay(int orderId, double amount) {
        System.out.println("💳 Payment method selected: UPI");
        System.out.printf("✅ Payment successful via UPI for amount ₹%.2f for order ID: #%d%n", amount, orderId);
    }
}