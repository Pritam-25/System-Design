// Class Invariant of a parent class Object should not be broken by child class Object.
// Hence child class can either maintain or strengthen the invariant but never narrows it down.

// Invariant: Balance cannot be negative
class BankAccount {

    protected double balance;

    public BankAccount(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance can't be negative");
        }
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount can't be negative");
        }

        if (balance - amount < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

// Break invariant: Should not be allowed.
class CheatAccount extends BankAccount {

    public CheatAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;  // LSP break! Negative balance allowed
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

public class ClassInvariants {

    public static void main(String[] args) {
        // BankAccount bankAccount = new BankAccount(-100);  //java.lang.IllegalArgumentException: Balance can't be negative
        BankAccount bankAccount = new BankAccount(1000);
        bankAccount.withdraw(200); // Amount withdrawn. Remaining balance is 800.0
        bankAccount.withdraw(850); // java.lang.RuntimeException: Insufficient funds
    }
}
