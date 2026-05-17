package models;

import java.util.List;

public class ParkingFloor {
    private String floorId;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId, List<ParkingSpot> parkingSpots) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
    }

    public String getFloorId() {
        return floorId;
    }

    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.canFit(vehicle)) {
                return spot;
            }
        }
        return null; // No available spot found
    }
}
