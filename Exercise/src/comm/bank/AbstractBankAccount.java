package comm.bank;

public abstract class AbstractBankAccount implements BankAccount {
    /**
     *
     */
    private double balance;
    /**
     *
     */
    private boolean isFrozen;
    /**
     *
     */
    public AbstractBankAccount() {
        balance = 0;
        isFrozen = false;
    }
    /**
     *
     * @param amount
     */
    public void setBalance(final double amount) {
        balance = amount;
    }
    /**
     *
     * @param amount
     */
    public void deposit(final double amount) {
        if (isFrozen) {
            System.out.println("Account is Frozen");
            System.out.println("Cannot Deposit");
            return;
         } else if (amount > 0) {
            setBalance(amount + getBalance());
            System.out.println("Deposited: Php " + amount);
            return;
         } else {
             System.out.println("The deposit amount must be positive.");
         }
    }
    /**
     * @param amount
     */
    public void withdraw(final double amount) {
        if (isFrozen) {
           System.out.println("Account is Frozen");
           System.out.println("Cannot Withdraw");
           return;
        } else if (amount <= getBalance() && amount > 0) {
           setBalance(getBalance() - amount);
           System.out.println("Withdrawn: Php " + amount);
           return;
        } else {
           if (amount > getBalance()) {
             System.out.println("Insufficient Balance");
             return;
           }
           System.out.println("The withdrawn amount must be positive.");
        }
    }
    /**
     * @return balance;
     */
    public double getBalance() {
        return balance;
    }
    /**
     *
     * @return isFrozen
     */
    public boolean isFrozen() {
        return isFrozen;
    }
    /**
     *
     */
    void freezeAccount() {
        System.out.println("Account has been frozen");
        isFrozen = true;
    }
    /**
     *
     */
    void unfreezeAccount() {
        System.out.println("Account has been unfrozen");
        isFrozen = false;
    }
}
