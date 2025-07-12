package Projects.JomatoClone.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Restaurant restaurant;
    private List<Dish> menu = new ArrayList<>();

    public Cart() {
        restaurant = null;
    }

    public void addItem(MenuItem item) {
        if (restaurant == null) {
            System.out.println("Cart: Set a restaurant before adding items.");
            return;
        }
        menu.add(item);
    }

    public double getTotalCost() {
        double sum = 0;
        for (MenuItem it : menu) {
            sum += it.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return restaurant == null || menu.isEmpty();
    }

    public void clear() {
        menu.clear();
        restaurant = null;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> gItems() {
        return menu;
    }
}
