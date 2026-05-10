interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " Paid using Credit Card");
    }
}

class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " Paid using Credit Card");
    }
}

class PaypalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " Paid using Credit Card");
    }
}

class PaymentService {
    private PaymentStrategy paymentStrategy;

    PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        PaymentStrategy creditcardPaymentStrategy = new CreditCardPayment();

        PaymentService paymentService = new PaymentService(creditcardPaymentStrategy);

        paymentService.processPayment(500);
    }
}