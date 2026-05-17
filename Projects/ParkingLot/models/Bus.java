package models;

import enums.VehicleType;

public class Bus extends Vehicle {
    public Bus(String licensePlate, String color) {
        super(licensePlate, VehicleType.BUS, color);
    }
}
