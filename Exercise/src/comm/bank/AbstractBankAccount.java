package comm.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBankAccount implements BankAccount {
    /**
     * List of transaction history.
     */
    private List<Transaction> transactionHistory;
    /**
     * The balance of the account.
     */
    private double balance;
    /**
     * Boolean value if account is frozen or not.
     */
    private boolean isFrozen;
    /**
     * Constructor, initialize account.
     */
    public AbstractBankAccount() {
        this.balance = 0;
        this.isFrozen = false;
        this.transactionHistory = new ArrayList<>();
    }
    /**
     * Deposit amount to the account.
     * @param amount
     */
    public void deposit(final double amount) throws AccountFrozenException,
                                                    InvalidAmountException {
        if (isFrozen) {
           throw new AccountFrozenException("Account is Frozen");
        }
        if (amount <= 0) {
           throw new InvalidAmountException(""
                   + "Amount is Invalid. Should be greater than 0");
        }
        transactionHistory.add(new Transaction("Deposit", amount));
        this.balance += amount;
        System.out.println("Deposited: Php " + amount);
    }
    /**
     * Withdraw amount from account.
     * @param amount
     */
    public void withdraw(final double amount) throws InsufficientFundsException,
                                                     AccountFrozenException,
                                                     InvalidAmountException {
        if (isFrozen) {
           throw new AccountFrozenException("Account is Frozen");
        }
        if (amount <= 0) {
           throw new InvalidAmountException(""
                   + "Amount is Invalid. Should be greater than 0");
        }
        if (amount > this.balance) {
           throw new InsufficientFundsException("Insufficient Balance");
        }
        transactionHistory.add(new Transaction("Withdraw", amount));
        this.balance -= amount;
        System.out.println("Withdrawn: Php " + amount);
    }
    /**
     * get value of balance in an account.
     * @return balance;
     */
    public double getBalance() {
        return this.balance;
    }
    /**
     * get the boolean value of isFrozen.
     * @return isFrozen
     */
    public boolean isFrozen() {
        return this.isFrozen;
    }
    /**
     * Set account to freeze.
     */
    public void freezeAccount() {
        this.isFrozen = true;
        System.out.println("Account has been frozen");
    }
    /**
     * UnFreeze account.
     */
    public void unfreezeAccount() {
        this.isFrozen = false;
        System.out.println("Account has been unfrozen");
    }
    /**
     * Return unmodifiable list of transaction view.
     * @return list
     */
    @Override
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
