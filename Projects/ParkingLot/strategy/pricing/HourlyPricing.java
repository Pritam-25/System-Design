package strategy.pricing;

import models.ParkingTicket;

public class HourlyPricing implements PricingStrategy {
    private double hourlyRate;

    public HourlyPricing(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        Long hoursParked = ticket.calculateDuration();
        return hoursParked * hourlyRate;
    }

}
