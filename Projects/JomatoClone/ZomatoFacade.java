package Projects.ZomatoClone;

import Projects.ZomatoClone.managers.*;
import Projects.ZomatoClone.models.*;
import Projects.ZomatoClone.service.NotificationManager;
import Projects.ZomatoClone.strategies.HaversineDistanceStrategy;
import Projects.ZomatoClone.strategies.IPaymentStrategy;
import Projects.ZomatoClone.strategies.SMSNotificationSender;

import java.util.List;

public class ZomatoFacade {

    public ZomatoFacade() {
        // Rider setup happens once automatically during app startup
        RiderAssignmentManager riderMgr = RiderAssignmentManager.getInstance();
        List<Rider> riders = List.of(
                new Rider("Rohit", new Location(22.57, 88.36)),
                new Rider("Anjali", new Location(22.58, 88.35))
        );
        riderMgr.setRiders(riders);
        riderMgr.setDistanceStrategy(new HaversineDistanceStrategy());
        System.out.println("✅ Riders configured in Facade constructor.");
    }


    // ✅ Create a new user
    public User createUser(String name, double lat, double lon) {
        Location location = new Location(lat, lon);
        User user = new User(name, location);
        System.out.println("👤 User created: " + user.getName() + ", Location: (" + lat + ", " + lon + ")");
        return user;
    }

    // ✅ Register a restaurant and add to RestaurantManager
    public Restaurant registerRestaurant(String name, String ownerName, double lat, double lon) {
        RestaurantOwner owner = new RestaurantOwner(ownerName);
        Location location = new Location(lat, lon);
        Restaurant restaurant = new Restaurant(name, owner, location);
        RestaurantManager.getInstance().createRestaurant(restaurant);

        System.out.println("🏪 Restaurant registered: " + name + ", Location: (" + lat + ", " + lon + ")");
        return restaurant;
    }

    // ✅ Add a dish to a restaurant's menu
    public void addDishToMenu(Restaurant restaurant, Dish dish) {
        restaurant.addMenuItem(dish);
        System.out.println("🍽️ Dish added to " + restaurant.getName() + " menu: " + dish.getName());
    }

    // ✅ Add item to user's cart
    public void addToCart(User user, Dish dish, int quantity) {
        user.getCart().addItem(dish, quantity);
        System.out.println("🛒 Added " + quantity + " x " + dish.getName() + " to cart");
    }

    // ✅ Place an order (only creates and accepts, no payment or processing)
    public Order placeOrder(User user) {
        Restaurant restaurant = user.getCart().getRestaurant();

        // Ensure restaurant is open
        boolean canAccept = RestaurantManager.getInstance().canAcceptOrder(restaurant);
        if (!canAccept) {
            System.out.println("🚫 Restaurant " + restaurant.getName() + " is currently closed.");
            return null;
        }

        System.out.println("🏪 Restaurant " + restaurant.getName() + " is open and can accept the order.");

        // Create order
        Order order = OrderManager.getInstance().createOrder(user);
        if (order == null) {
            System.out.println("❌ Order placement failed.");
        }

        return order;
    }

    // ✅ Set payment strategy and process payment
    public void makePayment(Order order, IPaymentStrategy strategy) {
        if (order == null) {
            System.out.println("⚠️ Cannot make payment: Order is null.");
            return;
        }

        order.setPaymentStrategy(strategy);
        order.processPayment();
        // ✅ Mark order as paid
        order.markPaid();
    }

    // ✅ Process full order after payment
    public void processOrder(Order order) {
        if (order == null) {
            System.out.println("⚠️ Cannot process a null order.");
            return;
        }

        // ✅ Now centralized: OrderManager handles food + delivery internally
        OrderManager.getInstance().processOrder(order);
    }
}
