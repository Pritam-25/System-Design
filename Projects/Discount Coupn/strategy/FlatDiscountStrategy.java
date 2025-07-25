class FlatDiscountStrategy implements DiscountStrategy{
    private double amount;

    public FlatDiscountStrategy(double amount){
        this.amount = amount;
    }

    public double calculate(double baseAmount){
        return baseAmount - amount;
    }
}