package Projects.ZomatoClone.models;

import Projects.ZomatoClone.utils.IdGeneration;

import java.util.List;
import java.util.Map;


public class Restaurant {
    private final int restaurantId;
    private String name;
    private Location location;
    private final Menu menu;
    private RestaurantOwner owner;
    private  boolean isOpen;

    public Restaurant(String name, RestaurantOwner owner, Location location) {
        this.name = name;
        this.owner = owner;
        this.location = location;
        this.menu = new Menu(name);
        this.restaurantId = IdGeneration.generateRestaurantId();
        this.isOpen = true;  // assuming restaurant is open by default
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getRestaurantId() {
        return restaurantId;
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

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public void addMenuItem(Dish item) {
        menu.addDish(item);
    }

    public List<Dish> getMenuItem() {
        return menu.getDishes();
    }


    // preparing food
    public boolean foodPrepareComplete(Order order, Map<Dish, Integer> dishes) {
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue();

            System.out.println("🥘 Preparing " + quantity + " x " + dish.getName() + "...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("⚠️ Error while preparing food: " + e.getMessage());
            }
        }
        return  true;
    }
}
