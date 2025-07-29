package coupon;

import model.Cart;

public abstract class Coupon {
    private Coupon next;

    public Coupon() {
        this.next = null;
    }
    public void setNext(Coupon next) {
        this.next = next;
    }
    public Coupon getNext() {
        return next;
    }

    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);

    public boolean isCombinable() {
        return true;
    }

    public abstract String name();

    public void applyDiscount(Cart cart){
        if(isApplicable(cart)){
            double discount = getDiscount(cart);
            cart.applyDiscount(discount);
            System.out.printf("Applied %s coupon: %.2f discount%n", name(), discount);
            if (!isCombinable()) {
                return;
            }
        } else {
            System.out.printf("%s coupon is not applicable.%n", name());
        }

        if (next != null) {
            next.applyDiscount(cart);
        }
    }
}
