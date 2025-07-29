package strategy;

public class DiscountStrategyManager {
    private static DiscountStrategyManager instance;

    private DiscountStrategyManager() {
        // Private constructor to prevent instantiation
    }

    public static DiscountStrategyManager getInstance() {
        if (instance == null) {
            instance = new DiscountStrategyManager();
        }
        return instance;
    }

    public DiscountStrategy getDiscountStrategy(StrategyType strategyType, double... params) {
        return switch (strategyType) {
            case FLAT -> new FlatDiscountStrategy(params[0]);
            case PERCENT -> new PercentageDiscountStrategy(params[0]);
            case PERCENT_WITH_CAP -> new PercentWithCapStrategy(params[0], params[1]);
            default -> {
                System.out.println("Invalid strategy type");
                yield null;
            }
        };
    }
}
