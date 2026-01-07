package comm.moviereservation;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {
    /**
     * Array list of movie show times and available tickets.
     */
    private static ArrayList<Movie> show = new ArrayList<>();
    //Add description for documentation purposes.
    /**
     * Time comparator to check if time is valid.
     */
    private static final String TIME_REGEX =
            "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s?(?i)(AM|PM)$";
    /**
     * Adding movies in the array show.
     */
    public MovieBookingSystem() {
        final int maxTicketsFor10AM = 50;
        final int maxTicketsFor1PM = 50;
        show.add(new Movie("10:00 AM", maxTicketsFor10AM));
        show.add(new Movie("1:00 PM", maxTicketsFor1PM));
    }
    /**
     * Checks the availability of each show time.
     * @param showTime
     * @return true if show time found and has available tickets,
     * false if no show time found.
     */
    public boolean checkAvailability(final String showTime) {
        Movie movie = getMovieByShowTime(showTime);
        if (movie != null && movie.getAvailableTickets() > 0) {
            return true;
        }
        return false;
    }
    /**
     * Books a ticket for a specified show time.
     * @param showTime
     * @param tickets
     */
    public void bookTicket(final String showTime, final int tickets) {
        if (!showTime.matches(TIME_REGEX)) {
            System.out.println("Error: Invalid showTime format.");
            return;
        }
        if (tickets <= 0) {
            System.out.println("Invalid ticket number");
            return;
        }
        String showTimeInput = showTime;
        String[] parts = showTimeInput .split("[:\\s]");
        int hour = Integer.parseInt(parts[0]);   // 1 or 01 → 1
        String minute = parts[1];                // 00–59
        String period = parts[2].toUpperCase();  // AM or PM
        String formattedShowTime = hour + ":" + minute + " " + period;
        Movie movie = getMovieByShowTime(formattedShowTime);
        if (!checkAvailability(formattedShowTime)) {
            System.out.println("Showtime not found!");
            return;
        }
        if (movie.getAvailableTickets() < tickets) {
            System.out.println(
                    "Not enough tickets available for this showtime");
            return;
        }
        movie.setAvailableTickets(
                movie.getAvailableTickets() - tickets);
        movie.setBookedTickets(
                movie.getBookedTickets() + tickets);
        System.out.println(tickets
                + " tickets successfully booked for "
                + showTime);
    }
    /**
     * Cancel reserved ticket for a specified show time.
     * @param showTime
     * @param tickets
     */
    public void cancelReservation(final String showTime, final int tickets) {
        if (!showTime.matches(TIME_REGEX)) {
            System.out.println("Error: Invalid showTime format.");
            return;
        }
        if (tickets <= 0) {
            System.out.println("Invalid ticket number");
            return;
        }
        String showTimeInput = showTime;
        String[] parts = showTimeInput .split("[:\\s]");
        int hour = Integer.parseInt(parts[0]);   // 1 or 01 → 1
        String minute = parts[1];                // 00–59
        String period = parts[2].toUpperCase();  // AM or PM
        String formattedShowTime = hour + ":" + minute + " " + period;

        Movie movie = getMovieByShowTime(formattedShowTime);
        if (!checkAvailability(formattedShowTime)) {
            System.out.println("Showtime not found!");
            return;
        }
        if (movie.getBookedTickets() < tickets) {
            System.out.println("Invalid operation "
                    + "(Attempt to cancel more tickets than booked)");
            return;
        }
        movie.setAvailableTickets(
                movie.getAvailableTickets() + tickets);
        movie.setBookedTickets(
                movie.getBookedTickets() - tickets);
        System.out.println(tickets
                + " tickets successfully cancelled for "
                + showTime);
    }
    /**
     * Display available shows with available tickets.
     */
    public void displayAvailableShows() {
        System.out.println("Available Shows: ");
        for (Movie movie : show) {
            System.out.println(movie.getShowTime()
                    + " - " + movie.getAvailableTickets()
                    + " Ticket Available");
        }
    }
    /**
     * Will get the object movie based on showTime.
     * @param showTime
     * @return movie
     */
    public Movie getMovieByShowTime(final String showTime) {
        for (Movie movie : show) {
            if (movie.getShowTime().equalsIgnoreCase(showTime)) {
                return movie;
            }
        }
        return null;
    }
    /**
     * Clear movie array for JUNIT testing.
     */
    static void resetShows() {
        show.clear();
    }
    /**
     * Main function.
     * @param args
     */
    public static void main(final String[] args) {
        final int fiveTicket = 5;
        final int hundredTicket = 100;
        final int threeTicket = 3;
        final int twoTicket = 2;
        MovieBookingSystem movie = new MovieBookingSystem();
        movie.displayAvailableShows();
        //Better if regex implementation should be clear since it
        //compares string values (e.g. 1:00 PM)
        //an display error format for "01:00 PM"
        movie.bookTicket("01:00 PM", twoTicket);
        //Booking and Cancellation of reservation should not allow 0
        //or negative values
        movie.bookTicket("10:00 AM", fiveTicket);
        movie.bookTicket("10:00 AM", hundredTicket);
        movie.cancelReservation("10:00 AM", threeTicket);
        movie.bookTicket("1:00 PM", twoTicket);
        movie.cancelReservation("1:00 PM", fiveTicket);
        movie.bookTicket("TenOclockAM", fiveTicket);
        movie.cancelReservation("TenOclockAM", fiveTicket);
        movie.bookTicket("12:00 AM", fiveTicket);
        movie.cancelReservation("12:00 PM", fiveTicket);
        movie.displayAvailableShows();
    }
}
