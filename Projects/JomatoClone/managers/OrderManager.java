package Projects.ZomatoClone.managers;

import Projects.ZomatoClone.models.*;
import Projects.ZomatoClone.enums.OrderStatus;
import Projects.ZomatoClone.service.NotificationManager;
import Projects.ZomatoClone.strategies.SMSNotificationSender;

public class OrderManager {

    private static volatile OrderManager instance;

    private OrderManager() {
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager();
                }
            }
        }
        return instance;
    }

    // ✅ Step 1: Create order from user and cart
    public Order createOrder(User user) {
        Cart cart = user.getCart();

        if (cart == null || cart.isEmpty()) {
            System.out.println("⚠️ Cannot place order: Cart is empty.");
            return null;
        }

        if (cart.getRestaurant() == null) {
            System.out.println("⚠️ Cannot place order: No restaurant selected.");
            return null;
        }

        Order order = new Order(user);
        order.setRestaurant(cart.getRestaurant());

        return order;
    }

    // ✅ Step 2: Process order after payment is done
    public void processOrder(Order order) {
        if (order == null) {
            System.out.println("⚠️ Cannot process null order.");
            return;
        }

        System.out.println("🍽️ Starting food preparation...");
        FoodManager.getInstance().prepareFood(order);
    }

    // ✅ Called by FoodManager when food is ready
    public void onFoodPrepared(Order order) {
        sendNotification(order, "🥗 Food prepared. Out for delivery soon!\"");

        // ✅ Step 3: Delegate to DeliveryManager
        DeliveryManager.getInstance().startDelivery(order);
    }

    // ✅ Called by DeliveryManager when order is delivered
    public void onOrderDelivered(Order order) {
        updateOrderStatus(order, OrderStatus.DELIVERED);;
        sendNotification(order, "🎊 Your order has been delivered. Enjoy your meal!");
    }

    // ✅ Common status updater
    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        OrderStatus oldStatus = order.getOrderStatus();
        order.setOrderStatus(newStatus);

        System.out.println("📦 Order #" + order.getOrderId() + " status: " + oldStatus + " → " + newStatus);

    }

    // ✅ Wrapper for sending notifications
    private void sendNotification(Order order, String message) {
        NotificationManager.getInstance().notifyUser(
                order.getUser().getName(),
                message,
                new SMSNotificationSender()
        );
    }
}
