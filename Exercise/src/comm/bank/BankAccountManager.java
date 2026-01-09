package comm.bank;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankAccountManager {
    /**
     * Stores bank accounts mapped by their unique account ID.
     */
    private Map<Integer, BankAccount> accounts;
    /**
     * Holds the next available account ID to be assigned.
     */
    private int nextAccountId;
    /**
     * Constructs a new bank account manager with no accounts.
     */
    public BankAccountManager() {
        accounts = new HashMap<>();
        nextAccountId = 1;
    }
    /**
     * Adds a new bank account to the manager and assigns it a unique ID.
     * @param account
     */
    void addAccount(final BankAccount account) {
        accounts.put(nextAccountId, account);
        nextAccountId++;
    }
    /**
     * Retrieves a bank account by its account ID.
     * @param accountId
     * @return account
     */
    BankAccount getAccount(final int accountId) throws NullPointerException {
        if (accounts.get(accountId) == null) {
            throw new NullPointerException("Null Pointer");
        }
        return accounts.get(accountId);
    }
    /**
     * Prints all managed bank accounts to the standard output.
     */
    void listAccount() {
        accounts.forEach((id, acc) -> System.out.println(acc));
    }
    /**
     * Filters transactions whose amount is greater than the specified value.
     * throws error if it is negative amount
     * @param amount
     * @param txList
     * @return list
     */
    List<Transaction> filterTransactionsAbove(final double amount,
                                              final List<Transaction> txList)
                                                 throws InvalidAmountException {
        if (amount < 0) {
            throw new InvalidAmountException("Invalid Amount for filtering");
        }
        return txList.stream()
                     .filter(tx ->  tx.getAmount() >= amount)
                     .collect(Collectors.toList());
    }
    /**
     * Sorts transactions in ascending order based on their amount.
     * @param txList
     * @return list
     */
    List<Transaction> sortTransactionsByAmount(final List<Transaction> txList) {
        return txList.stream()
                     .sorted(Comparator.comparingDouble(Transaction::getAmount))
                     .collect(Collectors.toList());
    }
}
