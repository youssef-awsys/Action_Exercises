package comm.bank;

import java.util.List;

public final class Main {
    /**
     *
     */
    private Main() {
        super();
    }
    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        final int[] money = {100, 500, 11500};
        try {
            BankAccountManager manager = new BankAccountManager();
            SavingsAccount account = new SavingsAccount("User");
            manager.addAccount(account);
            System.out.println(account.toString());
            account.deposit(money[1] + money[1]);
            try {
                account.deposit(0);
            } catch (InvalidAmountException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            try {
                account.deposit(-money[1]);
            } catch (InvalidAmountException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            account.withdraw(money[1]);
            try {
                account.withdraw(money[1] * 2);
            } catch (InsufficientFundsException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            try {
                account.withdraw(-money[1]);
            } catch (InvalidAmountException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            account.freezeAccount();
            try {
                account.deposit(money[1]);
            } catch (AccountFrozenException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            try {
                account.withdraw(money[1]);
            } catch (AccountFrozenException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            account.unfreezeAccount();
            account.withdraw(money[0]);
            System.out.println("Balance: Php " + account.getBalance());
            List<Transaction> history = account.getTransactionHistory();
            System.out.println("Transaction History: ");
            history.forEach(System.out::println);
            List<Transaction> filtered = manager.filterTransactionsAbove(
                    money[1], history);
            System.out.println("Transactions above 500:");
            filtered.forEach(System.out::println);
            try {
                List<Transaction> filteredInvalid =
                        manager.filterTransactionsAbove(
                        -money[1], history);
                System.out.println("Transactions above 500:");
                filteredInvalid.forEach(System.out::println);
            } catch (InvalidAmountException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            List<Transaction> sorted = manager.sortTransactionsByAmount(
                                                            history);
            System.out.println("Transactions sorted by amount:");
            sorted.forEach(System.out::println);
            try {
                System.out.println(manager.getAccount(2));
            } catch (NullPointerException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            System.out.println("List of Accounts:");
            manager.listAccount();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
