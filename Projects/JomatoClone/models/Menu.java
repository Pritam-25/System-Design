package Projects.JomatoClone.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String restaurantName;
    private List<Dish> menuItems;

    // No-arg constructor for frameworks or default usage
    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    // Constructor with null check for menuItems
    public Menu(String restaurantName, List<Dish> menuItems) {
        this.restaurantName = restaurantName;
        this.menuItems = (menuItems != null) ? menuItems : new ArrayList<>();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Dish> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Dish> menuItems) {
        this.menuItems = (menuItems != null) ? menuItems : new ArrayList<>();
    }

    // Add a new dish to the menu
    public void addMenuItem(Dish dish) {
        if (dish != null) {
            this.menuItems.add(dish);
        }
    }

    // Remove dish from menu
    public boolean removeMenuItem(Dish dish) {
        return this.menuItems.remove(dish);
    }

    // Find dish by name (case-insensitive)
    public Dish getDishByName(String name) {
        for (Dish dish : menuItems) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return dish;
            }
        }
        return null;
    }
}
