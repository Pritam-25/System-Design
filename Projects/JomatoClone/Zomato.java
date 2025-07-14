package Projects.JomatoClone;

import Projects.JomatoClone.models.*;
import Projects.JomatoClone.strategies.CreditCardIPaymentStrategy;

public class Jomato {
    public static void main(String[] args) {
        JomatoFacade app = new JomatoFacade();

        // 👤 1. Create a user
        User user = app.createUser("Pritam", 22.57, 88.36);

        // 🏪 2. Register a restaurant
        Restaurant restaurant = app.createRestaurant("Tandoori House", "Ravi", 22.58, 88.35);

        // 🍽️ 3. Add dishes to the restaurant's menu
        app.addDishToMenu(restaurant, new Dish("Butter Chicken", 300));
        app.addDishToMenu(restaurant, new Dish("Garlic Naan", 80));
        app.addDishToMenu(restaurant, new Dish("Paneer Tikka", 150));

        // 🛒 4. Add items to cart
        user.getCart().setRestaurant(restaurant);
        app.addToCart(user, new Dish("Butter Chicken", 300), 1);
        app.addToCart(user, new Dish("Garlic Naan", 80), 2);

        // 🧾 5. Place the order
        Order order = app.placeOrder(user);

        // 💳 6. Make payment
        app.makePayment(order, new CreditCardIPaymentStrategy("1234-5678-9012"));

        // 📦 7. Process the order (preparation, delivery, notification)
        app.processOrder(order);

        // 📃 8. Print invoice
        order.printInvoice();
    }
}
