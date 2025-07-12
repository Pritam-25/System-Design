package managers;

import enums.PlayStrategyType;
import strategies.CustomQueueStrategy;
import strategies.PlayStrategy;
import strategies.RandomPlayStrategy;
import strategies.SequentialPlayStrategy;

public class StrategyManager {
    private static StrategyManager instance = null;
    private SequentialPlayStrategy sequentialPlayStrategy;
    private RandomPlayStrategy randomPlayStrategy;
    private CustomQueueStrategy customQueueStrategy;

    //singleton pattern to ensure only one instance of StrategyManager exists
    private StrategyManager() {
        // Private constructor to prevent instantiation
        sequentialPlayStrategy = new SequentialPlayStrategy();
        randomPlayStrategy = new RandomPlayStrategy();
        customQueueStrategy = new CustomQueueStrategy();
    }

    public static synchronized StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }

    public PlayStrategy getStrategy(PlayStrategyType type) {
        if (type == PlayStrategyType.SEQUENTIAL) {
            return sequentialPlayStrategy;
        } else if (type == PlayStrategyType.RANDOM) {
            return randomPlayStrategy;
        } else if (type == PlayStrategyType.CUSTOM_QUEUE) {
            return customQueueStrategy;
        } else {
            throw new IllegalArgumentException("Unknown play strategy type: " + type);
        }
    }
}
