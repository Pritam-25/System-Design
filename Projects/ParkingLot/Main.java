import models.*;
import strategy.payment.UPIPayment;
import strategy.pricing.HourlyPricing;
import strategy.payment.PaymentStrategy;
import strategy.pricing.PricingStrategy;
import enums.SpotType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Small demo of typical flow for interview
        ParkingLot parkingLot = ParkingLot.getInstance("City Mall Parking", "Kolkata");

        Vehicle car = new Car("WB12AB1234", "Blue");

        // Build a floor with a few spots
        // Build floor and spots matching sample names
        ArrayList<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot("C1", SpotType.COMPACT));
        spots.add(new ParkingSpot("M1", SpotType.MOTORCYCLE));

        ParkingFloor floor = new ParkingFloor("F1", spots);
        parkingLot.addFloor(floor);

        EntrancePanel entrance = new EntrancePanel("Main Gate");
        // To match sample: 2 hours -> total fee 100.0 => hourly rate 50.0
        PricingStrategy pricing = new HourlyPricing(50.0);
        PaymentStrategy paymentMethod = new UPIPayment();
        ExitPanel exit = new ExitPanel("Exit Gate", paymentMethod, pricing);

        // Print header and creation
        System.out.println("========== PARKING LOT SYSTEM ==========\n");
        System.out.println("Parking Lot Created : " + parkingLot.getName());
        System.out.println("Floor Added : " + floor.getFloorId() + "\n");
        for (ParkingSpot s : spots) {
            System.out.println("Spot Added : " + s.getId() + " [" + s.getSpotType() + "]");
        }

        System.out.println("\n----------------------------------------\n");

        System.out.println("Vehicle Entered");
        System.out.println("License Plate : " + car.getLicensePlate());
        System.out.println("Vehicle Type  : " + car.getVehicleType() + "\n");

        System.out.println("Searching Available Spot...\n");

        ParkingSpot assigned = floor.findAvailableSpot(car);
        if (assigned != null) {
            assigned.park(car);
            System.out.println("Spot Assigned : " + assigned.getId() + "\n");
        } else {
            System.out.println("No spot available.");
            return;
        }

        ParkingTicket ticket = entrance.issueTicket(car, assigned);

        System.out.println("Parking Ticket Generated");
        System.out.println("Ticket Id : " + ticket.getTicketId());
        System.out.println("Entry Time : " + ticket.getissuedAt() + "\n");

        System.out.println("----------------------------------------\n");

        System.out.println("Vehicle Exiting...\n");
        System.out.println("Calculating Fee Using " + pricing.getClass().getSimpleName());
        long hrs = ticket.calculateDuration();
        System.out.println("Parking Duration : " + hrs + " Hours");
        double fee = pricing.calculateFee(ticket);
        System.out.println("Total Fee : " + fee + "\n");

        Payment payment = exit.processExit(ticket);

        payment.markSuccessful(); // Simulate successful payment for demo

        if (payment.isSuccess()) {
            System.out.println("Payment Successful\n");
        }

        System.out.println(payment.getReceipt());

        System.out.println("Ticket Status Updated : " + ticket.getStatus() + "\n");

        assigned.removeVehicle();
        System.out.println("----------------------------------------\n");
        System.out.println("Vehicle Removed From Spot : " + assigned.getId() + "\n");
        System.out.println("Thank You Visit Again\n");
        System.out.println("========================================");
    }
}