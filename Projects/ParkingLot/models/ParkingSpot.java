package models;

import enums.SpotType;
import enums.VehicleType;

public class ParkingSpot {
    private String id;
    private SpotType spotType;
    private boolean isAvailable;
    private Vehicle vehicle;

    public ParkingSpot(String id, SpotType spotType) {
        this.id = id;
        this.spotType = spotType;
        this.isAvailable = true;
        this.vehicle = null;
    }

    // Public helpers used by callers
    public boolean isAvailable() {
        return isAvailable;
    }

    public String getId() {
        return id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    public boolean canFit(Vehicle vehicle) {
        if (this.isAvailable) {
            if (this.spotType == SpotType.MOTORCYCLE) {
                return vehicle.getVehicleType() == VehicleType.MOTORCYCLE;
            } else if (this.spotType == SpotType.COMPACT) {
                return vehicle.getVehicleType() == VehicleType.CAR
                        || vehicle.getVehicleType() == VehicleType.MOTORCYCLE;
            } else if (this.spotType == SpotType.LARGE) {
                return true; // Can fit any vehicle type
            }
        }
        return false;
    }
}
