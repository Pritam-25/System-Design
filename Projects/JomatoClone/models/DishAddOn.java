package Projects.ZomatoClone.models;

public class DishAddOn {
    private String name;
    private double price;

    public DishAddOn(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    //* Optional: override toString() for easy debugging/logging
    @Override
    public String toString() {
        return name + " (₹" + price + ")";
    }
}
