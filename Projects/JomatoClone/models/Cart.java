package Projects.ZomatoClone.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Restaurant restaurant;
    private Map<Dish, Integer> items;

    public Cart() {
        this.restaurant = null;
        this.items = new HashMap<>();
    }

    public void setRestaurant(Restaurant r) {
        this.restaurant = r;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void addItem(Dish dish, int quantity) {
        if (restaurant == null) {
            System.out.println("🛑 Set a restaurant before adding items to the cart.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("⚠️ Quantity must be at least 1.");
            return;
        }

        items.put(dish, items.getOrDefault(dish, 0) + quantity);
    }

    public Map<Dish, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        double total = 0;
        for (Map.Entry<Dish, Integer> entry : items.entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue();
            total += dish.getPrice() * quantity;
        }
        return total;
    }

    public boolean isEmpty() {
        return restaurant == null || items.isEmpty();
    }

    public void clear() {
        items.clear();
        restaurant = null;
        System.out.println("🧹 Cart cleared");
    }
}
