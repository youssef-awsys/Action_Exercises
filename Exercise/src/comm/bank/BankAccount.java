package comm.bank;

import java.util.List;

public interface BankAccount {
    /**
     * Deposits a specified amount into the account.
     * @param amount
     * @throws InvalidAmountException
     * @throws AccountFrozenException
     * @throws InsufficientFundsException
     */
    void deposit(double amount) throws InsufficientFundsException,
                                       AccountFrozenException,
                                       InvalidAmountException;
    /**
     * Withdraws a specified amount from the account.
     * @param amount
     * @throws InsufficientFundsException
     * @throws AccountFrozenException
     * @throws InvalidAmountException
     */
    void withdraw(double amount) throws InsufficientFundsException,
                                        AccountFrozenException,
                                        InvalidAmountException;
    /**
     * Returns the current account balance.
     * @return balance
     */
    double getBalance();
    /**
     * Indicates whether the account is currently frozen.
     * @return isFrozen
     */
    boolean isFrozen();
    /**
     * Freezes the account, preventing deposits and withdrawals.
     */
    void freezeAccount();
    /**
     * UnFreezes the account, allowing deposits and withdrawals.
     */
    void unfreezeAccount();
    /**
     * Returns the list of the transaction history.
     * @return list
     */
    List<Transaction> getTransactionHistory();
}
