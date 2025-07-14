package Projects.ZomatoClone.models;

import Projects.ZomatoClone.enums.Rating;

public class RestaurantOwner {
    private final String name;
    private Rating rating;

    public RestaurantOwner(String name) {
        this.name = name;
        this.rating = Rating.UNASSIGNED; // Default rating
    }

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
