package strategy.pricing;

import models.ParkingTicket;

public interface PricingStrategy {
    double calculateFee(ParkingTicket ticket);
}