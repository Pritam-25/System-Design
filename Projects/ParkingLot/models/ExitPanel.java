package models;

import java.util.UUID;

import enums.PaymentType;
import strategy.payment.PaymentStrategy;
import strategy.pricing.PricingStrategy;
import enums.TicketStatus;

public class ExitPanel {
    private String panelId;
    private String location;

    private PaymentStrategy paymentStrategy;
    private PricingStrategy pricingStrategy;

    public ExitPanel(String location, PaymentStrategy paymentStrategy, PricingStrategy pricingStrategy) {
        this.panelId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        this.location = location;
        this.paymentStrategy = paymentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Payment processExit(ParkingTicket ticket) {
        double fee = pricingStrategy.calculateFee(ticket);

        boolean paymentSuccess = paymentStrategy.pay(fee);

        String ticketId = ticket.getTicketId();

        Payment payment = new Payment(ticketId, fee, PaymentType.UPI);

        if (paymentSuccess) {
            payment.markSuccessful();
            ticket.setStatus(TicketStatus.PAID);
            ticket.setFee(fee);
        }
        return payment;
    }
}
