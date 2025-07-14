package Projects.ZomatoClone.service;

import Projects.ZomatoClone.models.Location;

public interface DistanceStrategy {
    double calculateDistance(Location start, Location end);
}
