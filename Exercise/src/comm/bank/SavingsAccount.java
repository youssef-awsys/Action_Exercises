package comm.bank;

public class SavingsAccount extends AbstractBankAccount {
    /**
     * Counter used to assign unique IDs to each savings account.
     */
    private static int idCounter = 1;
    /**
     * The unique ID of this savings account.
     */
    private int accountId;
    /**
     * The name of the account owner.
     */
    private String ownerName;
    /**
     * Constructs a new savings account for the specified owner.
     * Automatically assigns a unique account ID.
     * @param name
     */
    public SavingsAccount(final String name) {
        ownerName = name;
        accountId = idCounter;
        idCounter++;
    }
    /**
     * Returns the name of the account owner.
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }
    /**
     * Returns the unique ID of this savings account.
     * @return accountId
     */
    public int getAccountId() {
        return accountId;
    }
    /**
     * Returns a string representation of the savings account,
     * including the owner's name, account ID, and current balance.
     */
    @Override
    public String toString() {
        return "Owner: " + getOwnerName()
                + ", ID: " + getAccountId()
                + ", Balance: Php " + getBalance();
    }
}
