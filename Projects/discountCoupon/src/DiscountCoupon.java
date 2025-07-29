import coupon.*;
import model.Cart;
import model.Product;

import java.util.List;

public class DiscountCoupon {
    public static void main(String[] args) {
        // Example usage of the CouponManager and Coupons
        CouponManager couponManager = CouponManager.getInstance();
        
        couponManager.registerCoupon(new SeasonalOffer(10, "Clothing"));
        couponManager.registerCoupon(new LoyaltyDiscount(5));
        couponManager.registerCoupon(new BulkPurchaseDiscount(1000, 100));
        couponManager.registerCoupon(new BankingCoupon("ABC", 2000, 15, 500));

        
        Product product1 = new Product("Laptop", "Electronics", 1200.00);
        Product product2 = new Product("Shoes", "Fashion", 80.00);
        Product product3 = new Product("Book", "Education", 20.00);
        Product product4 = new Product("Headphones", "Electronics", 2000);

        // Create a cart and add products
        Cart cart = new Cart();
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 3);
        cart.addProduct(product3, 2);
        cart.addProduct(product4, 4);
        cart.setLoyaltyMember(true);
        cart.setPaymentBank("Laptop");

        System.out.println("Original price of the cart: " + cart.getOriginalPrice() + " Rs");

        List<String> applicableCoupons = couponManager.getApplicable(cart);
        System.out.println("Applicable coupons:");
        for (String coupon : applicableCoupons) {
            System.out.println("- " + coupon);
        }

        // Apply all coupons to the cart
        double finalPrice = couponManager.applyAll(cart);
        System.out.printf("Final price after applying coupons: %.2f%n", finalPrice);
    }
}
