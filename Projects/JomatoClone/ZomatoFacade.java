package Projects.JomatoClone;

import Projects.JomatoClone.managers.DeliveryManager;
import Projects.JomatoClone.managers.FoodManager;
import Projects.JomatoClone.managers.OrderManager;
import Projects.JomatoClone.managers.RestaurantManager;
import Projects.JomatoClone.models.*;
import Projects.JomatoClone.service.NotificationManager;
import Projects.JomatoClone.strategies.IPaymentStrategy;
import Projects.JomatoClone.strategies.SMSNotificationSender;

public class JomatoFacade {

    public User createUser(String name, double lat, double lon) {
        Location location = new Location(lat, lon);
        User user = new User(name, location);
        System.out.println("👤 User created: " + user.getName() + ", Location: (" + lat + ", " + lon + ")");
        return user;
    }


    public Restaurant registerRestaurent(String name, String ownerName, double lat, double lon) {
        RestaurantOwner owner = new RestaurantOwner(ownerName);
        Location loc = new Location(lat, lon);
        Restaurant restaurant = new Restaurant(name, owner, loc);

        // ✅ Register it in RestaurantManager
        RestaurantManager.getInstance().createRestaurant(restaurant);


        System.out.println("🏪 Restaurant registered: " + name + ", Location: (" + lat + ", " + lon + ")");
        return restaurant;
    }

    public void addDishToMenu(Restaurant restaurant, Dish dish) {
        restaurant.addMenuItem(dish);
        System.out.println("🍽️ Dish added to " + restaurant.getName() + " menu: " + dish.getName());
    }

    public void addToCart(User user, Dish dish, int quantity) {
        user.getCart().addItem(dish, quantity);
        System.out.println("🛒 Added " + quantity + " " + dish.getName() + " to cart");
    }

    public Order placeOrder(User user) {
        Restaurant restaurant = user.getCart().getRestaurant();

        // ✅ Pre-order logs
        System.out.println("🔍 RestaurantManager checking if restaurant " + restaurant.getName() + " can accept the order...");

        if (!RestaurantManager.getInstance().canAcceptOrder(restaurant)) {
            System.out.println("🚫 Restaurant " + restaurant.getName() + " is currently closed.");
            return null;
        }

        System.out.println("🏪 Restaurant " + restaurant.getName() + " is open and can accept the order.");

        // ✅ Create order from user
        Order order = OrderManager.getInstance().createOrder(user);

        if (order != null) {
            System.out.println("🧾 Order placed: Order #" + order.getOrderId());
            System.out.println("🙎 RestaurantManager processing order #" + order.getOrderId());
            System.out.println("📦 Order status updated: PENDING → PROCESSING");
        }

        return order;
    }

    public void makePayment(Order order, IPaymentStrategy strategy) {
        order.setPaymentStrategy(strategy);
        order.processPayment();
    }

    public void processOrder(Order order) {
        // ✅ Prepare food
        Restaurant restaurant = order.getRestaurant();
        FoodManager.getInstance().prepareFood(order.getOrderId(), restaurant, order.getItems());


        // ✅ Assign delivery
        DeliveryManager.getInstance().assignDelivery(order);

        // ✅ Send notification
        NotificationManager.getInstance().notifyUser(order.getUser().getName(),
                "Your order has been delivered. Enjoy your meal!",
                new SMSNotificationSender());

        System.out.println("🎉 Order #" + order.getOrderId() + " completed successfully!");
    }
}
