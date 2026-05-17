package models;

import java.time.LocalDateTime;
import java.util.UUID;

import enums.PaymentType;

public class Payment {
    private String paymentId;
    private String ticketId;
    private double amount;
    private PaymentType paymentType;
    private LocalDateTime paidAt;
    private boolean isSuccess;

    public Payment(String ticketId, double amount, PaymentType paymentType) {
        this.paymentId = "P" + (1000 + (int) (Math.random() * 9000));
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.isSuccess = false;
    }

    public void markSuccessful() {
        this.isSuccess = true;
        this.paidAt = LocalDateTime.now();
    }

    public String getReceipt() {
        return String.format(
                "Payment Receipt:\nPayment ID: %s\nTicket ID: %s\nAmount: $%.2f\nPayment Type: %s\nPaid At: ",
                paymentId, ticketId, amount, paymentType, paidAt);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
