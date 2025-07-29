package coupon;

import model.Cart;
import strategy.DiscountStrategy;
import strategy.DiscountStrategyManager;
import strategy.StrategyType;

public class LoyaltyDiscount extends Coupon{
    private double percentage;
    private DiscountStrategy strategy;

    public LoyaltyDiscount(double percentage) {
        this.percentage = percentage;
        this.strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(StrategyType.PERCENT,     10.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.isLoyaltyMember();
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculateDiscount(cart.getFinalPrice());
    }

    @Override
    public String name() {
        return "Loyalty Discount " + (int) percentage + "% off";
    }
}
