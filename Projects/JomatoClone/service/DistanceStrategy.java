package Projects.JomatoClone.strategies;

import Projects.JomatoClone.models.Location;

public interface DistanceStrategy {
    double calculateDistance(Location start, Location end);
}
