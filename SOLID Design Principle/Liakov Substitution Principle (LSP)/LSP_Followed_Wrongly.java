import java.util.ArrayList;
import java.util.List;

interface Account {
    void deposit(double amount);

    void withdraw(double amount);
}

class SavingAccount implements Account {
    private double balance;

    public SavingAccount() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit:" + amount + "in Savings Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Savings Account. New Balance: " + balance);
        }
    }
}

class CurrentAccount implements Account {
    private double balance;

    public CurrentAccount() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit:" + amount + "in Current Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Current Account. New Balance: " + balance);
        }
    }
}

class FixedTerm implements Account {
    private double balance;

    public FixedTerm() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit:" + amount + "in Fixed Term Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawal not allowed from Fixed Term Amount");
    }
}

class BankClient {
    private List<Account> accounts;

    public BankClient(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void processTransaction() {
        for (Account acc : accounts) {
            acc.deposit(1000); // All accounts allow deposits

             // Checking account type explicitly
             //* break OCP (Open-Closed Principle)
            if (acc instanceof FixedTerm) {
                System.out.println("Skipping withdrawal from Fixed term account");
            } else {
                try {
                    acc.withdraw(500); 
                } catch (UnsupportedOperationException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }

        }
    }
}

public class LSP_Followed_Wrongly {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new CurrentAccount());
        accounts.add(new SavingAccount());
        accounts.add(new FixedTerm());

        BankClient bankClient = new BankClient(accounts);
        bankClient.processTransaction();
    }
}
