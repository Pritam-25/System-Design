// ✅ Class ensures that allowed behaviors in the base class are not removed in subclass
class BankAccount {
    protected double balance;

    public BankAccount(double b) {
        if (b < 0) throw new IllegalArgumentException("Balance can't be negative");
        this.balance = b;
    }

    // ✅ History Constraint: withdraw is always allowed
    public void withdraw(double amount) {
        if (balance - amount < 0) throw new RuntimeException("Insufficient funds");
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

// ✅ Correct subclass that adds behavior but does not remove it
class FixedDepositAccount extends BankAccount {

    private final long createdTime;
    private final long maturityPeriodMillis = 60_000L; // 1 minute for demo

    public FixedDepositAccount(double b) {
        super(b);
        this.createdTime = System.currentTimeMillis();
    }

    // ✅ Withdraw is allowed **only** after maturity
    @Override
    public void withdraw(double amount) {
        long now = System.currentTimeMillis();
        if (now - createdTime < maturityPeriodMillis) {
            throw new RuntimeException("Withdraw not allowed before maturity");
        }
        super.withdraw(amount); // Delegate to parent behavior
    }
}

public class Fix_HistoryConstraint {
    public static void main(String[] args) throws InterruptedException {
        BankAccount acc1 = new BankAccount(500);
        acc1.withdraw(100); // ✅ works immediately

        BankAccount acc2 = new FixedDepositAccount(1000);
        Thread.sleep(61000); // Simulate waiting till maturity (61 sec)
        acc2.withdraw(300); // ✅ Now allowed after maturity
    }
}
