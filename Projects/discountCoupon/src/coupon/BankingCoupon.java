package coupon;

import model.Cart;
import strategy.DiscountStrategy;
import strategy.DiscountStrategyManager;
import strategy.StrategyType;

public class BankingCoupon extends Coupon {
    private String bankName;
    private double minSpend;
    private double percentageOff;
    private double offCap;
    private DiscountStrategy strategy;


    public BankingCoupon(String bankName, double minSpend, double percentageOff, double offCap) {
        this.bankName = bankName;
        this.minSpend = minSpend;
        this.percentageOff = percentageOff;
        this.offCap = offCap;
        this.strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(StrategyType.PERCENT_WITH_CAP, percentageOff, offCap);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getPaymentBank().equals(bankName) && cart.getOriginalPrice() >= minSpend;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculateDiscount(cart.getFinalPrice());
    }

    @Override
    public String name() {
        return bankName + " Bank Rs " + (int) percentageOff + "% off, max Rs " + (int) offCap + " on minimum spend of Rs " + (int) minSpend;
    }
}
