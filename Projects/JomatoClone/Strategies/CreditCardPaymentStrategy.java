package Projects.ZomatoClone.strategies;

public class CreditCardPaymentStrategy implements IPaymentStrategy {
    private final String cardNumber;

    public CreditCardPaymentStrategy(String cardNo) {
        this.cardNumber = cardNo;
    }

    private String maskCardNumber(String cardNumber){
        // Remove non-digits
        String clean = cardNumber.replaceAll("\\D","");
        int length = clean.length();

        if(length < 4) return "****";

        String last4 = clean.substring(length-4);
        return "****"+last4;
    }

    @Override
    public void pay(int orderId, double amount) {
        System.out.println("💳 Payment method selected: Credit Card (" + maskCardNumber(cardNumber) + ")");
        System.out.printf("✅ Payment successful via Credit Card for amount ₹%.2f for order ID: #%d%n", amount, orderId);

    }
}