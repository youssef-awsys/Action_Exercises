package comm.moviereservation;

public class Movie {
    /**
     * The Show Time of a Movie.
     */
    private String showTime;
    /**
     * Number of Available tickets of a Movie.
     */
    private int availableTickets;
    /**
     * Number of Booked Tickets of a Movie.
     */
    private int bookedTickets;
    /**
     * Constructs a new movie object.
     * Initialize no tickets are booked
     * @param showTime1
     * @param availableTickets1
     */
    public Movie(
            final String showTime1,
            final int availableTickets1) {
        this.showTime = showTime1;
        this.availableTickets = availableTickets1;
        this.bookedTickets = 0;
    }
    /**
     * Get the show time of the movie.
     * @return showTime
     */
    public String getShowTime() {
        return showTime;
    }
    /**
     * Get the Available ticket of the movie.
     * @return available tickets
     */
    public int getAvailableTickets() {
        return availableTickets;
    }
    /**
     * get the number of booked ticket of the movie.
     * @return bookedTickets
     */
    public int getBookedTickets() {
        return bookedTickets;
    }
    /**
     * Updates the available tickets.
     * @param availableTicket
     */
    public void setAvailableTickets(final int availableTicket) {
        this.availableTickets = availableTicket;
    }
    /**
     * Updates the booked tickets.
     * @param bookedTicket
     */
    public void setBookedTickets(final int bookedTicket) {
        this.bookedTickets = bookedTicket;
    }
}

