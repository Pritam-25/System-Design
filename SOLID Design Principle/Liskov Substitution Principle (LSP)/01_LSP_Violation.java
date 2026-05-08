/*
❌ WHY THIS VIOLATES LSP

Rule:
If Account is expected, ANY subclass should work without breaking code.

Problem:
- BankClient assumes ALL Account support withdraw()
- But FixedTerm throws exception ❌

So:
Account acc = new FixedTerm();
acc.withdraw() → 💥 breaks program

👉 This means subclass (FixedTerm) cannot replace parent (Account)

👉 Hence LSP is violated
*/

interface Account {
    void deposit(double amount);

    void withdraw(double amount); // ❌ forced on all
}

class SavingAccount implements Account {
    public void deposit(double amount) {
        System.out.println("Saving deposit: " + amount);
    }

    public void withdraw(double amount) {
        System.out.println("Saving withdraw: " + amount);
    }
}

class FixedTerm implements Account {
    public void deposit(double amount) {
        System.out.println("FixedTerm deposit: " + amount);
    }

    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw ❌");
    }
}

class LSP_Violation {
    public static void main(String[] args) {
        Account acc = new FixedTerm();

        acc.deposit(1000);

        // 💥 BOOM → breaks expectation
        acc.withdraw(500);
    }
}