package Projects.ZomatoClone.managers;

import Projects.ZomatoClone.enums.OrderStatus;
import Projects.ZomatoClone.models.Order;
import Projects.ZomatoClone.models.Restaurant;

public class FoodManager {

    // Singleton instance
    private static FoodManager instance;

    private FoodManager() {
    }

    public static FoodManager getInstance() {
        if (instance == null) {
            instance = new FoodManager();
        }
        return instance;
    }


    public void prepareFood(Order order) {
        System.out.println("👨‍🍳 FoodManager started preparing food for Order#" + order.getOrderId());
        OrderManager.getInstance().updateOrderStatus(order, OrderStatus.PREPARING);

        Restaurant restaurant = order.getRestaurant();

        // Simulate food preparation
        boolean isPrepared = restaurant.foodPrepareComplete(order, order.getItems());

        if (isPrepared) {
            System.out.println("✅ Food prepared for Order #" + order.getOrderId());
            OrderManager.getInstance().updateOrderStatus(order, OrderStatus.READY_FOR_PICKUP);

            // 🔁 Call back to OrderManager to trigger delivery
            OrderManager.getInstance().onFoodPrepared(order);
        } else {
            System.out.println("❌ Food preparation failed for Order #" + order.getOrderId());
        }
    }

}
