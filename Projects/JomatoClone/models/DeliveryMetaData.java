package Projects.ZomatoClone.models;

public class DeliveryMetaData {
    private Rider assignedRider;
    private Location pickupLocation;
    private Location dropLocation;

    public void setAssignedRider(Rider rider) {
        this.assignedRider  = rider;
    }

    public Rider getAssignRider(){
        return assignedRider;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }
}
