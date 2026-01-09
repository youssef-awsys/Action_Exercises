package comm.bank;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
    /**
     * Constructor.
     * @param s
     */
    public InsufficientFundsException(final String s) {
        super(s);
    }
}
