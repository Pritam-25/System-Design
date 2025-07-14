package Projects.ZomatoClone.strategies;

import Projects.ZomatoClone.models.Location;
import Projects.ZomatoClone.service.DistanceStrategy;

public class HaversineDistanceStrategy implements DistanceStrategy {

    @Override
    public double calculateDistance(Location start, Location end) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(end.getLatitude() - start.getLatitude());
        double lonDistance = Math.toRadians(end.getLongitude() - start.getLongitude());

        double sinLat = Math.sin(latDistance / 2);
        double sinLon = Math.sin(lonDistance / 2);

        double aCalc = sinLat * sinLat +
                Math.cos(Math.toRadians(start.getLatitude())) * Math.cos(Math.toRadians(end.getLatitude())) *
                sinLon * sinLon;

        double c = 2 * Math.atan2(Math.sqrt(aCalc), Math.sqrt(1 - aCalc));
        return R * c; // Distance in kilometers
    }
}
