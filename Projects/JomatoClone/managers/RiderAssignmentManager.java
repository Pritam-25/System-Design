package Projects.JomatoClone.managers;

import Projects.JomatoClone.models.Rider;


public class RiderAssignmentManager {
    private final List<Rider> riders;
    private final DistanceStrategy distanceStrategy;

    public RiderAssignmentManager(List<Rider> riders, DirSanceStrategy dirSanceStrategy) {
        this.riders = riders;
        this.distanceStrategy = dirSanceStrategy;
    }

    public Rider assignRider(Order order) {
        Rider bestRider = null;
        double minDistance = Double.MAX_VALUE;

        for (Rider rider : riders) {
            if (rider.isAvailable()) {
                double distance = distanceStrategy.calculateDistance(rider.getCurentLocation(), order.getDeliveryLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestRider = rider;
                }
            }
        }
        if (bestRider != null) {
            bestRider.setAvailable(false); // Mark the rider as unavailable once assigned
            return bestRider;
        } else {
            System.out.println("No available riders at the moment.");
            return null;
        }
    }
}
