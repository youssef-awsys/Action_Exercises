package comm.library;

public class NonFictionBook extends Book {
    /**
     *
     * @param title
     * @param author
     * @param yearPublished
     */
    public NonFictionBook(
            final String title,
            final String author,
            final int yearPublished) {
        super(title, author, yearPublished);
    }
}
