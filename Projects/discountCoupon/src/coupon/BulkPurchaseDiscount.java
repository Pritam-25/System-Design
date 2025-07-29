package coupon;

import model.Cart;
import strategy.DiscountStrategy;
import strategy.DiscountStrategyManager;
import strategy.StrategyType;

public class BulkPurchaseDiscount extends Coupon {
    private final double threshold;
    private final double flatOff;
    private final DiscountStrategy strategy;

    public BulkPurchaseDiscount(double threshold, double flatOff) {
        this.threshold = threshold;
        this.flatOff = flatOff;
        this.strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(StrategyType.FLAT, 0.0);
    }


    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getOriginalPrice() >= threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculateDiscount(cart.getFinalPrice());
    }

    @Override
    public String name() {
        return "Bulk Purchase Rs "+(int)flatOff + " off over " + (int)threshold;
    }
}
