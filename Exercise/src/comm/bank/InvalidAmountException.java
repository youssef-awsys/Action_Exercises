package comm.bank;

@SuppressWarnings("serial")
public class InvalidAmountException extends Exception {
    /**
     * Constructor.
     * @param s
     */
    public InvalidAmountException(final String s) {
        super(s);
    }
}
