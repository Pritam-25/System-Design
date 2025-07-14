package Projects.ZomatoClone.managers;

import Projects.ZomatoClone.enums.OrderStatus;
import Projects.ZomatoClone.models.Order;
import Projects.ZomatoClone.models.Rider;
import Projects.ZomatoClone.service.DistanceStrategy;
import java.util.ArrayList;
import java.util.List;

public class RiderAssignmentManager {
    private static RiderAssignmentManager instance;
    private final List<Rider> riders = new ArrayList<>();
    private DistanceStrategy distanceStrategy;

    private RiderAssignmentManager() {}

    public static RiderAssignmentManager getInstance() {
        if (instance == null) {
            instance = new RiderAssignmentManager();
        }
        return instance;
    }

    public void setRiders(List<Rider> riders) {
        this.riders.clear();
        this.riders.addAll(riders);
    }

    public void setDistanceStrategy(DistanceStrategy strategy) {
        this.distanceStrategy = strategy;
    }

    public Rider assignRider(Order order) {
        Rider bestRider = null;
        double minDistance = Double.MAX_VALUE;

        System.out.println("📍 Finding nearest rider using HaversineDistanceStrategy...");
        for (Rider rider : riders) {
            if (rider.isAvailable()) {
                double distance = distanceStrategy.calculateDistance(
                        rider.getCurentLocation(), order.getDeliveryLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestRider = rider;
                }
            }
        }

        if (bestRider != null) {
            bestRider.setAvailable(false);
            order.setOrderStatus(OrderStatus.ASSIGNED_TO_DELIVERY);
            System.out.println("🚲 Rider " + bestRider.getRiderName() + " assigned to order " + order.getOrderId());
            return bestRider;
        } else {
            System.out.println("❌ No available riders for order #" + order.getOrderId());
            return null;
        }
    }
}
