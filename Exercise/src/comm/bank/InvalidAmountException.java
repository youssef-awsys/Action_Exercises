package comm.bank;

@SuppressWarnings("serial")
public class InvalidAmountException extends Exception {
    //Improve description of the documentation
    /**
     * Constructor creates a new InvalidAmountException.
     * @param s
     */
    public InvalidAmountException(final String s) {
        super(s);
    }
}
