package Projects.JomatoClone.Strategies;

public class UpiPaymentStrategy implements PaymentStrategy {
    private final String mobile;

    public UpiPaymentStrategy(String mob) {
        this.mobile = mob;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI (" + mobile + ")");
    }

}