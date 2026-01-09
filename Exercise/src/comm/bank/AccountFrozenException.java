package comm.bank;

@SuppressWarnings("serial")
public class AccountFrozenException extends Exception {
    /**
     * Constructs a new Account Frozen Exception.
     * @param s
     */
    public AccountFrozenException(final String s) {
        super(s);
    }
}
