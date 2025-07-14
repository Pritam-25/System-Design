package Projects.ZomatoClone;

import Projects.ZomatoClone.models.*;
import Projects.ZomatoClone.service.NotificationManager;
import Projects.ZomatoClone.strategies.CreditCardPaymentStrategy;
import Projects.ZomatoClone.strategies.SMSNotificationSender;

public class ZomatoApplication {
    public static void main(String[] args) {

        // 🧠 Create Facade
        ZomatoFacade zomato = new ZomatoFacade();

        // 👤 Step 1: Create User
        User user = zomato.createUser("Pritam", 22.57, 88.36);

        // 🏪 Step 2: Register Restaurant
        Restaurant restaurant = zomato.registerRestaurant("Tandoori House", "Chef Arjun", 22.58, 88.35);

        // 🍽️ Step 3: Add Dishes to Restaurant Menu
        zomato.addDishToMenu(restaurant, new Dish("Butter Chicken", 300));
        zomato.addDishToMenu(restaurant, new Dish("Garlic Naan", 80));
        zomato.addDishToMenu(restaurant, new Dish("Paneer Tikka", 250));

        //set restaurant in user's cart
        user.getCart().setRestaurant(restaurant);
        // 🛒 Step 4: Add Items to Cart
        zomato.addToCart(user, new Dish("Butter Chicken", 300), 1);
        zomato.addToCart(user, new Dish("Garlic Naan", 80), 2);

        // 📦 Step 5: Place Order
        Order order = zomato.placeOrder(user);

        // 💳 Step 6: Make Payment
        zomato.makePayment(order, new CreditCardPaymentStrategy("1234-5678-9012-3456"));


// ✅ 3. Only if payment is successful, process the order
        if (order.isPaid()) {
            System.out.println("🧾 Order placed: Order #" + order.getOrderId());

            // 🔔 Notify user
            NotificationManager.getInstance().notifyUser(order.getUser().getName(), "Your order has been accepted!", new SMSNotificationSender());
            zomato.processOrder(order);
        } else {
            System.out.println("🚫 Cannot process unpaid order.");
        }

        // 🧾 Step 8: Print Final Invoice
        order.printInvoice();
    }
}
