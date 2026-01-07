package comm.moviereservation;

public abstract class BookingSystem {
    /**
     * Checks how many tickets are available for the specified show time.
     * @param showTime
     * @return tickets
     */
    public abstract boolean checkAvailability(String showTime);
    /**
     * Book a specified number of tickets for a given show time.
     * @param showTime
     * @param tickets
     */
    public abstract void bookTicket(String showTime, int tickets);
    /**
     * Cancels a specified number of tickets for a given show time.
     * @param showTime
     * @param tickets
     */
    public abstract void cancelReservation(String showTime, int tickets);
}
