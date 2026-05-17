package strategy.pricing;

import models.ParkingTicket;

public class FlatPricing implements PricingStrategy {
    private double flatRate;

    public FlatPricing(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        return flatRate;
    }
}
