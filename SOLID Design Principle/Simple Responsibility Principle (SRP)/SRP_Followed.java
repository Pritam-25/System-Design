import java.util.ArrayList;
import java.util.List;

// Product class representing an item in the cart
class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// ShoppingCart class: Only handles cart logic (SRP)
class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.price;
        }
        return total;
    }
}

// Responsible for printing the invoice only
class ShoppingCartPrinter {
    private ShoppingCart cart;

    public ShoppingCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        for (Product p : cart.getProducts()) {
            System.out.println(p.name + " - $" + p.price);
        }
        System.out.println("Total: $" + cart.calculateTotal());
    }
}

// Responsible for storing cart data (e.g., to DB)
class ShoppingCartStorage {
    private ShoppingCart cart;

    public ShoppingCartStorage(ShoppingCart cart) {
        this.cart = cart;
    }

    public void saveToDatabase() {
        System.out.println("Saving shopping cart details to database...");
        // Simulation only — actual DB logic not implemented
    }
}

// Main class to run the program
public class SRP_Followed {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Product p1 = new Product("Protein Powder", 2500);
        Product p2 = new Product("Creatine", 500);

        cart.addProduct(p1);
        cart.addProduct(p2);

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        ShoppingCartStorage db = new ShoppingCartStorage(cart);
        db.saveToDatabase();
    }
}

