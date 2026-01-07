package comm.moviereservation;

public class Movie {
    /**
     *
     */
    private String showTime;
    /**
     *
     */
    private int availableTickets;
    /**
     *
     */
    private int bookedTickets;
    /**
     *
     * @param showTime1
     * @param availableTickets1
     */
    public Movie(
            final String showTime1,
            final int availableTickets1) {
        this.showTime = showTime1;
        this.availableTickets = availableTickets1;
    }
    /**
     *
     * @return showTime
     */
    public String getShowTime() {
        return showTime;
    }
    /**
     *
     * @return available tickets
     */
    public int getAvailableTickets() {
        return availableTickets;
    }
    /**
     *
     * @return bookedTickets
     */
    public int getBookedTickets() {
        return bookedTickets;
    }
    /**
     *
     * @param availableTicket
     */
    public void setAvailableTickets(final int availableTicket) {
        this.availableTickets = availableTicket;
    }
    /**
     *
     * @param bookedTicket
     */
    public void setBookedTickets(final int bookedTicket) {
        this.bookedTickets = bookedTicket;
    }
}

