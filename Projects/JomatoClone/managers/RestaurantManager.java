package Projects.ZomatoClone.managers;

import Projects.ZomatoClone.models.Restaurant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantManager {
    // singleton instance
    private static volatile RestaurantManager instance;

    // Thread-safe restaurant manager
    private final Map<String, Restaurant> restaurants;

    // private constructor to prevent instantiation
    private RestaurantManager() {
        this.restaurants = new ConcurrentHashMap<>();
    }

    // Double-checked locking for singleton instance
    public static RestaurantManager getInstance() {
        if (instance == null) {
            synchronized (RestaurantManager.class) {
                if (instance == null) {
                    instance = new RestaurantManager();
                }
            }
        }
        return instance;
    }

    // Register a restaurant
    public void createRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getName(), restaurant);
    }

    // Get restaurant by name
    public Restaurant getRestaurantByName(String name) {
        return restaurants.get(name);
    }

    // Check if a restaurant is open
    public boolean canAcceptOrder(Restaurant restaurant) {
        System.out.println("🔍 RestaurantManager checking if restaurant " + restaurant.getName() + " can accept the order...");

        if (!restaurant.isOpen()) {
            System.out.println("🚫 Restaurant " + restaurant.getName() + " is currently closed.");
            return false;
        }
        return restaurant.isOpen();
    }

    // Optional: print all registered restaurants
    public void printAllRestaurants() {
        System.out.println("📋 Registered Restaurants:");
        for (Restaurant r : restaurants.values()) {
            System.out.println(" - " + r.getName() + " (ID: " + r.getRestaurantId() + ")");
        }
    }
}
