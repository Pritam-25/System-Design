package Projects.ZomatoClone.models;

import Projects.ZomatoClone.enums.OrderStatus;
import Projects.ZomatoClone.strategies.IPaymentStrategy;
import Projects.ZomatoClone.utils.IdGeneration;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private final int orderId;
    private final User user;
    private final Location deliveryLocation;

    private Restaurant restaurant;
    private final Map<Dish, Integer> items;
    private IPaymentStrategy paymentStrategy;

    private final double foodAmount;
    private static final double GST_RATE = 0.18;
    private static final double DELIVERY_CHARGE = 40.0;
    private double totalCost;

    private OrderStatus orderStatus;
    private DeliveryMetaData deliveryMetaData;
    private boolean isPaid = false;


    // ✅ Constructor
    public Order(User user) {
        this.orderId = IdGeneration.generateOrderId();
        this.user = user;
        this.deliveryLocation = user.getLocation();
        this.restaurant = user.getCart().getRestaurant();
        this.items = new HashMap<>(user.getCart().getItems());
        this.paymentStrategy = null;
        this.orderStatus = OrderStatus.PENDING;

        this.foodAmount = user.getCart().getTotalCost();
        calculateTotal(); // Immediately calculate cost
    }

    public void markPaid() {
        this.isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public DeliveryMetaData getDeliveryMetaData() {
        return deliveryMetaData;
    }

    public void setDeliveryMetaData(DeliveryMetaData deliveryMetaData) {
        this.deliveryMetaData = deliveryMetaData;
    }

    // ✅ Total Calculation: subtotal + GST + delivery
    private void calculateTotal() {
        double gst = foodAmount * GST_RATE;
        totalCost = foodAmount + gst + DELIVERY_CHARGE;
    }

    // ✅ Payment processing
    public void processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.pay(orderId, totalCost);
        } else {
            System.out.println("⚠️ Please choose a payment mode first.");
        }
    }

    // ✅ Print Invoice
    public void printInvoice() {
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("🧾 Order Summary (Invoice):");

        System.out.println("Items:");
        for (Map.Entry<Dish, Integer> entry : items.entrySet()) {
            Dish dish = entry.getKey();
            int qty = entry.getValue();
            double cost = dish.getPrice() * qty;
            System.out.printf(" - %s (x%d): ₹%.2f%n", dish.getName(), qty, cost);
        }

        System.out.println("--------------------------------");
        System.out.printf("Subtotal: ₹%.2f%n", foodAmount);
        System.out.printf("GST (18%%): ₹%.2f%n", foodAmount * GST_RATE);
        System.out.printf("Delivery Charge: ₹%.2f%n", DELIVERY_CHARGE);
        System.out.printf("Total Payable: ₹%.2f%n", totalCost);
        System.out.println("--------------------------------");
    }


    // ✅ Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Map<Dish, Integer> getItems() {
        return items;
    }


    public double getTotal() {
        return totalCost;
    }

    public void setPaymentStrategy(IPaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public IPaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
