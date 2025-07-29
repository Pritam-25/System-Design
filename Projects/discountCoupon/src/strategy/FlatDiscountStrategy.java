package strategy;

public class FlatDiscountStrategy implements DiscountStrategy {
    private final double discountAmount;

    public FlatDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double calculateDiscount(double baseAmount) {
        return Math.min(discountAmount, baseAmount); // Ensure discount does not exceed base amount
    }
}
