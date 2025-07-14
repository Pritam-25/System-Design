package Projects.ZomatoClone.utils;

import java.security.SecureRandom;

public class IdGeneration {
    private static final SecureRandom random = new SecureRandom();

    // Generates a random 6-digit number for order IDs
    public static int generateOrderId() {
        return 100000 + random.nextInt(900000);
    }

    // Generates a random 4-digit number for user IDs
    public static int generateUserId() {
        return 1000 + random.nextInt(9000);
    }

    // generates a random 5-digit number for restaurant IDs
    public static int generateRestaurantId() {
        return 10000 + random.nextInt(90000);
    }
}
