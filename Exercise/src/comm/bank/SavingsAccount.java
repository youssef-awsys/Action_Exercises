package comm.bank;

public class SavingsAccount extends AbstractBankAccount {
    /**
     *
     */
    private String ownerName;
    /**
     *
     * @param name
     */
    public SavingsAccount(final String name) {
        ownerName = name;
    }
    /**
     *
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }
}
