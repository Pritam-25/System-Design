package coupon;

import model.Cart;
import model.CartItem;
import strategy.DiscountStrategy;
import strategy.DiscountStrategyManager;
import strategy.StrategyType;

public class SeasonalOffer extends Coupon {
    private final double percent;
    private final String category;
    private final DiscountStrategy strategy;

    public SeasonalOffer(double percent, String category) {
        this.percent = percent;
        this.category = category;
        this.strategy = DiscountStrategyManager.getInstance()
                .getDiscountStrategy(StrategyType.PERCENT, 0.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        for (CartItem product : cart.getProducts()) {
            if (product.getProduct().getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double subtotal = 0.0;
        for (CartItem product : cart.getProducts()) {
            if (product.getProduct().getCategory().equals(category)) {
                subtotal += product.getTotalPrice();
            }
        }
        return strategy.calculateDiscount(subtotal);
    }

    @Override
    public String name() {
        return "Seasonal Offer " + (int) percent + "% off" + category;
    }
}
