// 🚚 DeliveryManager.java
package Projects.ZomatoClone.managers;

import Projects.ZomatoClone.enums.OrderStatus;
import Projects.ZomatoClone.models.*;
import Projects.ZomatoClone.service.NotificationManager;
import Projects.ZomatoClone.strategies.SMSNotificationSender;

public class DeliveryManager {

    private static final DeliveryManager instance = new DeliveryManager();

    private DeliveryManager() {
    }

    public static DeliveryManager getInstance() {
        return instance;
    }

    public void startDelivery(Order order) {
        if (order == null || order.getRestaurant() == null) {
            System.out.println("❌ Delivery assignment failed: Incomplete order information.");
            return;
        }
        System.out.println("🚚 DeliveryManager managing delivery for Order#" + order.getOrderId());

        Rider rider = RiderAssignmentManager.getInstance().assignRider(order); // get assigned manager

        if (rider == null) {
            return;
        }

        // create delivery metadata
        DeliveryMetaData meta = new DeliveryMetaData();
        meta.setAssignedRider(rider);
        meta.setPickupLocation(order.getRestaurant().getLocation());
        meta.setDropLocation(order.getUser().getLocation());
        order.setDeliveryMetaData(meta);

        OrderManager.getInstance().updateOrderStatus(order, OrderStatus.ASSIGNED_TO_DELIVERY);
        NotificationManager.getInstance().notifyUser(order.getUser().getName(), "Your order is out for delivery with " + rider.getRiderName(), new SMSNotificationSender());
        simulateDelivery(order);
    }

    private void simulateDelivery(Order order) {
        OrderManager orderMgr = OrderManager.getInstance();
        try {
            Thread.sleep(2000);

            orderMgr.updateOrderStatus(order, OrderStatus.PICKED_UP);
            NotificationManager.getInstance().notifyUser(order.getUser().getName(), "Your order has been picked up by the rider.", new SMSNotificationSender());
            Thread.sleep(3000);

            orderMgr.onOrderDelivered(order);
        } catch (InterruptedException e) {
            System.out.println("⚠️ Delivery interrupted: " + e.getMessage());
        }
    }
}
