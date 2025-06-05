import java.util.ArrayList;
import java.util.List;

// Interface for deposit-only accounts
interface DepositOnlyAccount {
    void deposit(double amount);
}

// Interface for accounts that support withdrawal (extends deposit functionality)
interface WithdrawableAccount extends DepositOnlyAccount {
    void withdraw(double amount);
}

// Savings Account supports both deposit and withdrawal
class SavingAccount implements WithdrawableAccount {
    private double balance;

    public SavingAccount() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + " in Savings Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Savings Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Savings Account.");
        }
    }
}

// Current Account supports both deposit and withdrawal
class CurrentAccount implements WithdrawableAccount {
    private double balance;

    public CurrentAccount() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + " in Current Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Current Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Current Account.");
        }
    }
}

// Fixed Term Account only supports deposit, no withdrawal
class FixedTerm implements DepositOnlyAccount {
    private double balance;

    public FixedTerm() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + " in Fixed Term Account. New Balance: " + balance);
    }
}

// BankClient handles all account operations
class BankClient {
    private List<DepositOnlyAccount> depositAccounts;
    private List<WithdrawableAccount> withdrawAccounts;

    public BankClient(List<DepositOnlyAccount> depositAccounts, List<WithdrawableAccount> withdrawAccounts) {
        this.depositAccounts = depositAccounts;
        this.withdrawAccounts = withdrawAccounts;
    }

    public void processTransactions() {
        // All accounts get deposit
        for (DepositOnlyAccount account : depositAccounts) {
            account.deposit(1000);
        }

        // Only eligible accounts get withdrawal
        for (WithdrawableAccount account : withdrawAccounts) {
            account.deposit(1000);
            account.withdraw(500);
        }
    }
}

// Entry point
public class LSP_Followed {
    public static void main(String[] args) {
        List<DepositOnlyAccount> depositAccounts = new ArrayList<>();
        List<WithdrawableAccount> withdrawAccounts = new ArrayList<>();

        // Create accounts
        SavingAccount savingAccount = new SavingAccount();
        CurrentAccount currentAccount = new CurrentAccount();
        FixedTerm fixedTerm = new FixedTerm();

        // Add accounts to appropriate lists
        withdrawAccounts.add(savingAccount);
        withdrawAccounts.add(currentAccount);

        depositAccounts.add(fixedTerm);

        // Create client and process transactions
        BankClient client = new BankClient(depositAccounts, withdrawAccounts);
        client.processTransactions();
    }
}
