// Class Invariant: Balance cannot be negative
class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance can't be negative");
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Withdrawal amount can't be negative");

        if (balance - amount < 0)
            throw new RuntimeException("Insufficient funds");

        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

// ✅ Fixed version: Child class maintains (or strengthens) the invariant
class SecureAccount extends BankAccount {

    public SecureAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        // Strengthened invariant: do not allow withdrawals above 500 at once
        if (amount > 500)
            throw new IllegalArgumentException("Cannot withdraw more than 500 at once");

        // Still respects the parent invariant
        super.withdraw(amount);
    }
}

public class Fix_ClassInvariants {
    public static void main(String[] args) {
        // ✅ Test with BankAccount
        BankAccount bankAccount = new BankAccount(1000);
        bankAccount.withdraw(200); // Amount withdrawn. Remaining balance is 800.0
        // bankAccount.withdraw(850); // ❌ Throws: RuntimeException - Insufficient funds

        // ✅ Test with SecureAccount (subclass)
        BankAccount secureAccount = new SecureAccount(1000);
        secureAccount.withdraw(400); // OK
        // secureAccount.withdraw(600); // ❌ Throws: IllegalArgumentException - more than 500 not allowed
        // secureAccount.withdraw(700); // ❌ and would also break parent invariant if allowed
    }
}
