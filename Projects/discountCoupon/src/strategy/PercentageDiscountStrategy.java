package strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double discountPercentage;

    public PercentageDiscountStrategy(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            System.out.println("Discount percentage must be between 0 and 100");
        }
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateDiscount(double baseAmount) {
        return baseAmount * (discountPercentage / 100);
    }
}
