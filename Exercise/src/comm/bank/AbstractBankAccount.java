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
        this.balance = 0;
        this.isFrozen = false;
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
            this.balance += amount;
            System.out.println("Deposited: Php " + amount);
            return;
         } else {
             System.out.println("The deposit amount must be positive.");
         }
    }
    /**
     *
     * @param amount
     */
    public void withdraw(final double amount) {
        if (isFrozen) {
           System.out.println("Account is Frozen");
           System.out.println("Cannot Withdraw");
           return;
        } else if (amount <= this.balance && amount > 0) {
           this.balance -= amount;
           System.out.println("Withdrawn: Php " + amount);
           return;
        } else {
           if (amount > this.balance) {
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
        return this.balance;
    }
    /**
     *
     * @return isFrozen
     */
    public boolean isFrozen() {
        return this.isFrozen;
    }
    /**
     *
     */
    void freezeAccount() {
        this.isFrozen = true;
        System.out.println("Account has been frozen");
    }
    /**
     *
     */
    void unfreezeAccount() {
        this.isFrozen = false;
        System.out.println("Account has been unfrozen");
    }
}
