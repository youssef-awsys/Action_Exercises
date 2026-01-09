package comm.bank;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
    //Improve description for documentation purposes
    /**
     * Constructor creates a new InsufficientFundsException.
     * @param s
     */
    public InsufficientFundsException(final String s) {
        super(s);
    }
}
