
import java.util.ArrayList;
import java.util.List;

// Product class representing any item of any ECommerce
class Product {

    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Violating SRP: ShoppingCart handles cart logic, printing, and storage
class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    // Adds a product to the cart
    public void addProduct(Product p) {
        products.add(p);
    }

    // Returns all products
    public List<Product> getProducts() {
        return products;
    }

    // Calculates total price
    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.price;
        }
        return total;
    }

    // Violating SRP: Printing responsibility
    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        for (Product p : products) {
            System.out.println(p.name + " - $" + p.price);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    // Violating SRP: Saving to database responsibility
    public void saveToDatabase() {
        System.out.println("Saving shopping cart details to database...");
    }
}

public class SRP_Violated {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Protein Powder", 2500));
        cart.addProduct(new Product("Creatine", 500));

        cart.printInvoice();       // SRP Violation
        cart.saveToDatabase();     // SRP Violation
    }
}
