package Projects.JomatoClone.models;

public class Rider {
    private final String riderId;
    private Location location;
    private boolean isAvailable;
    private double rating;


    public Rider(String riderId, Location location, double rating) {
        this.riderId = riderId;
        this.location = location;
        this.isAvailable = true;
        this.rating = rating;
    }

    public String getRiderId() {
        return riderId;
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
