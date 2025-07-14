package Projects.ZomatoClone.models;

import Projects.ZomatoClone.utils.IdGeneration;

public class User {
    private final int userId;
    private String name;
    private Location location;
    private final Cart cart;

    public User(String name, Location location) {
        this.userId = IdGeneration.generateUserId(); // Assuming it returns a String like "ORD12345"
        this.name = name;
        this.location = location;
        this.cart = new Cart();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Cart getCart() {
        return cart;
    }
}
