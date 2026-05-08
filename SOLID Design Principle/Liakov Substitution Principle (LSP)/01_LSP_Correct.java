/*
✅ WHY THIS FOLLOWS LSP

Fix:
- Separate behaviors into interfaces
- Only classes that support withdraw implement it

Now:
- No class is forced to do something it cannot
- No runtime crash
- Substitution works perfectly

👉 Every object behaves as expected
👉 LSP satisfied
*/

interface DepositAccount {
    void deposit(double amount);
}

interface WithdrawAccount extends DepositAccount {
    void withdraw(double amount);
}

class SavingAccount implements WithdrawAccount {
    public void deposit(double amount) {
        System.out.println("Saving deposit: " + amount);
    }

    public void withdraw(double amount) {
        System.out.println("Saving withdraw: " + amount);
    }
}

class FixedTerm implements DepositAccount {
    public void deposit(double amount) {
        System.out.println("FixedTerm deposit: " + amount);
    }
}

class LSP_Correct {
    static void main() {

        DepositAccount fixed = new FixedTerm();
        fixed.deposit(1000); // ✅ works

        WithdrawAccount saving = new SavingAccount();
        saving.deposit(1000);
        saving.withdraw(500); // ✅ works

        // ❌ You CANNOT even call withdraw on FixedTerm now
        // So no crash → design is safe
    }
}