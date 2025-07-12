package Projects.JomatoClone.Strategies;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private final String cardNumber;

    public CreditCardPaymentStrategy(String cardNo) {
        this.cardNumber = cardNo;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card (" + cardNumber + ")");
    }
}