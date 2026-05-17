package models;

import enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate, String color) {
        super(licensePlate, VehicleType.CAR, color);
    }
}
