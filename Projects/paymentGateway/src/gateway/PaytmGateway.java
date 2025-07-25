package gateway;

import bank.PaytmBankingSystem;
import model.PaymentRequest;

public class PaytmGateway extends PaymentGateway {
    public PaytmGateway() {
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    public boolean validatePayment(PaymentRequest request){
        System.out.println("[Paytm] Validating payment for " + request.sender + "...");
        if(request.amount <= 0 || !"INR".equals(request.currency)){
            return false;
        }
        return true;
    }

    @Override
    public boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Initiating payment of " + request.amount
                + " " + request.currency + " for " + request.sender + "...");
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    public boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Paytm] Confirming payment for " + request.sender + "...");
        // Simulate confirmation logic
        return true; // Assume confirmation is always successful
    }
}
