package Projects.JomatoClone;

import Projects.JomatoClone.models.*;
import Projects.JomatoClone.strategies.CreditCardIPaymentStrategy;
import Projects.JomatoClone.JomatoFacade;

public class JomatoApplication {
    public static void main(String[] args) {

        // 🧠 Create Facade
        JomatoFacade zomato = new JomatoFacade();

        // 👤 Step 1: Create User
        User user = zomato.createUser("Pritam", 22.57, 88.36);

        // 🏪 Step 2: Register Restaurant
        Restaurant restaurant = zomato.registerRestaurent("Tandoori House", "Chef Arjun", 22.58, 88.35);

        // 🍽️ Step 3: Add Dishes to Restaurant Menu
        zomato.addDishToMenu(restaurant, new Dish("Butter Chicken", 300));
        zomato.addDishToMenu(restaurant, new Dish("Garlic Naan", 80));
        zomato.addDishToMenu(restaurant, new Dish("Paneer Tikka", 250));

        // 🛒 Step 4: Add Items to Cart
        user.getCart().setRestaurant(restaurant);
        app.addToCart(user, new Dish("Butter Chicken", 300), 1);
        app.addToCart(user, new Dish("Garlic Naan", 80), 2);

        // 📦 Step 5: Place Order
        Order order = zomato.placeOrder(user);

        // 💳 Step 6: Make Payment
        zomato.makePayment(order, new CreditCardPayment("1234-5678-9012-3456"));

        // 🔄 Step 7: Process Order (Prepare + Deliver + Notify)
        zomato.processOrder(order);

        // 🧾 Step 8: Print Final Invoice
        order.printInvoice();
    }
}
