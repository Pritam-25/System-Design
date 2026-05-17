package models;

import java.util.ArrayList;
import java.util.List;

import enums.LotStatus;

public class ParkingLot {
    private static ParkingLot instance;
    private String name;
    private String address;
    private LotStatus status;
    private List<ParkingFloor> parkingFloors;

    private ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.status = LotStatus.OPEN;
        this.parkingFloors = new ArrayList<>();
    }

    public static ParkingLot getInstance(String name, String address) {
        if (instance == null) {
            instance = new ParkingLot(name, address);
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public String getName() {
        return name;
    }
}
