package models;

import java.util.UUID;

public class EntrancePanel {
    private String panelId;
    private String location;

    public EntrancePanel(String location) {
        this.panelId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        this.location = location;
    }

    public ParkingTicket issueTicket(Vehicle vehicle, ParkingSpot spot) {
        return new ParkingTicket(vehicle, spot);
    }
}
