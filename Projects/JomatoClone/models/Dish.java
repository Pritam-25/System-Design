package Projects.ZomatoClone.models;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String name;
    private String description;
    private double price;
    private List<String> dishImages;
    private List<DishAddOn> addOns;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.dishImages = new ArrayList<>();
        this.addOns = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p) {
        this.price = p;
    }

    public List<String> getDishImages() {
        return dishImages;
    }

    public void setDishImages(List<String> dishImages) {
        this.dishImages = dishImages;
    }

    public List<DishAddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<DishAddOn> addOns) {
        this.addOns = addOns;
    }

    // Convenience methods
    public void addImage(String imageUrl) {
        this.dishImages.add(imageUrl);
    }

    public void addAddOn(DishAddOn addOn) {
        this.addOns.add(addOn);
    }
}
