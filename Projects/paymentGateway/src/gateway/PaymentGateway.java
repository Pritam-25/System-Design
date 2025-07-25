package gateway;

import bank.BankingSystem;
import model.PaymentRequest;

public abstract class PaymentGateway {

    protected BankingSystem bankingSystem;

    public PaymentGateway() {
        this.bankingSystem = null;
    }

    public boolean processPayment(PaymentRequest request) {
        if (!validatePayment(request)) {
            System.out.println("[PaymentGateway] Payment validation failed for " + request.sender);
            return false;
        }
        if (!initiatePayment(request)) {
            System.out.println("[PaymentGateway] Payment initiation failed for " + request.sender);
            return false;
        }
        if (!confirmPayment(request)) {
            System.out.println("[PaymentGateway] Payment processing failed for " + request.sender);
            return false;
        }
        ;
        return true;
    }

    public abstract boolean validatePayment(PaymentRequest request);

    public abstract boolean initiatePayment(PaymentRequest request);

    public abstract boolean confirmPayment(PaymentRequest request);
}
