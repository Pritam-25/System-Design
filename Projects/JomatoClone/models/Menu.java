package Projects.ZomatoClone.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String restaurantName;
    private List<Dish> dishes;

    // Constructor to initialize the menu with a restaurant name
    public Menu(String restaurantName) {
        this.restaurantName = restaurantName;
        this.dishes = new ArrayList<>();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> menuItems) {
        this.dishes = (menuItems != null) ? menuItems : new ArrayList<>();
    }

    // Add a new dish to the menu
    public void addDish(Dish dish) {
        if (dish != null) {
            this.dishes.add(dish);
        }
    }

    // Remove dish from menu
    public void removeDish(Dish dish) {
        this.dishes.remove(dish);
    }

    // Find dish by name (case-insensitive)
    public Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return dish;
            }
        }
        return null;
    }

    public void printMenu() {
        System.out.println("📜 Menu for " + (restaurantName != null ? restaurantName : "Restaurant") + ":");
        for (Dish dish : dishes) {
            System.out.println(" - " + dish.getName() + " ₹" + dish.getPrice());
        }
    }
}
