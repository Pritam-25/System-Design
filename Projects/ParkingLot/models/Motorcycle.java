package models;

import enums.VehicleType;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate, String color) {
        super(licensePlate, VehicleType.MOTORCYCLE, color);
    }
}
