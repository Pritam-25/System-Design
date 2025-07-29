package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> products = new ArrayList<>();
    private double originalPrice = 0.0;
    private double finalPrice = 0.0;
    private boolean loyaltyMember = false;
    private String paymentBank = "";

    public void addProduct(Product product, int quantity) {
        CartItem item = new CartItem(product, quantity);
        products.add(item);
        originalPrice += item.getTotalPrice();
        finalPrice = originalPrice; // Initially, final price is the same as original
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void applyDiscount(double discount) {
        finalPrice = finalPrice - discount;
        if (finalPrice < 0) {
            finalPrice = 0; // Ensure final price does not go below zero
        }
    }

    public void setLoyaltyMember(boolean isMember) {
        this.loyaltyMember = isMember;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setPaymentBank(String bank) {
        this.paymentBank = bank;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public List<CartItem> getProducts() {
        return products;
    }
}
