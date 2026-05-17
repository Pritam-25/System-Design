package models;

import enums.VehicleType;

public abstract class Vehicle {
    private String licensePlate;
    private VehicleType vehicleType;
    private String color;

    public Vehicle(String licensePlate, VehicleType vehicleType, String color) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

}
