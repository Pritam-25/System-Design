package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import enums.TicketStatus;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime issuedAt;
    private LocalDateTime exitAt;
    private double fee;
    private TicketStatus status;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        // short hex id
        this.ticketId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        this.vehicle = vehicle;
        this.spot = spot;
        this.issuedAt = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    // Getters and setters
    public String getTicketId() {
        return ticketId;
    }

    public String getissuedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");

        String formattedTime = issuedAt.format(formatter);
        return formattedTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public long calculateDuration() {
        if (exitAt == null) {
            exitAt = LocalDateTime.now();
        }
        return Math.max(1, Duration.between(issuedAt, exitAt).toHours()); // Minimum 1 hour charge
    }
}
