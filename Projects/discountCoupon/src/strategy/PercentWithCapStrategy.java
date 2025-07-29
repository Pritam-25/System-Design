package strategy;

public class PercentWithCapStrategy implements  DiscountStrategy {
    private final double discountPercentage;
    private final double maxDiscountAmount;

    public PercentWithCapStrategy(double percentage, double maxDiscountAmount) {
        this.discountPercentage = percentage;
        this.maxDiscountAmount = maxDiscountAmount;
    }

    @Override
    public double calculateDiscount(double baseAmount) {
        double discount = (discountPercentage / 100.0) * baseAmount;
        return Math.min(discount, maxDiscountAmount);
    }
}
