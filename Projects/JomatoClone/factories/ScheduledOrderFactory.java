package Projects.JomatoClone.factories;

import java.util.List;

import Projects.JomatoClone.Strategies.PaymentStrategy;
import Projects.JomatoClone.models.Cart;
import Projects.JomatoClone.models.MenuItem;
import Projects.JomatoClone.models.Order;
import Projects.JomatoClone.models.Restaurant;
import Projects.JomatoClone.models.User;

public class ScheduledOrderFactory implements OrderFactory {

    private String scheduleTime;

    public ScheduledOrderFactory(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItem,
            PaymentStrategy paymentStrategy, double totalCost, String orderType) {
        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else if (orderType.equals("Pickup")) {
            PickupOrder pickupOrder = new PickupOrder();
            pickupOrder.setRestaurantAddress(restaurant.getLocation());
            order = pickupOrder;
        } else {
            throw new IllegalArgumentException("Invalid order type. Please choose between [\"Pickup\", \"Delivery\"]");
        }

        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItem);
        order.setPaymentStrategy(paymentStrategy);
        order.setScheduled(scheduleTime);
        order.setTotal(totalCost);

        return order;
    }
}
