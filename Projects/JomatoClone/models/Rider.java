package Projects.ZomatoClone.models;

import Projects.ZomatoClone.utils.IdGeneration;

public class Rider {
    private String name;
    private final int riderId;
    private Location location;
    private boolean isAvailable;
    private double rating;


    public Rider(String name, Location location) {
        this.name = name;
        this.riderId = IdGeneration.generateUserId();
        this.location = location;
        this.isAvailable = true; // assuming rider is available by default
        this.rating = 0.0; // default rating
    }

    public int getRiderId() {
        return this.riderId;
    }

    public String getRiderName() {
        return this.name;
    }

    public Location getCurentLocation() {
        return location;
    }

    public void setCurentLocation(Location location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }
}
