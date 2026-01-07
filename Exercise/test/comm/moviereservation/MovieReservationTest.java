package comm.moviereservation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieReservationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    void setup(){
        MovieBookingSystem.resetShows();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void testBookTickets_withValidTicketNumber_ReturnSuccessfulBooked() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("10:00 AM", 5);
        String output = outContent.toString();
        Movie movie = book.getMovieByShowTime("10:00 AM");
        assertEquals(45, movie.getAvailableTickets());
        assertEquals(5, movie.getBookedTickets());
        assertTrue(output.contains("tickets successfully booked"));
    }
    @Test
    void testBookTickets_withMoreThanAvailableTicket_ReturnNotEnoughTicket() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("10:00 AM", 100);
        String output = outContent.toString();
        Movie movie = book.getMovieByShowTime("10:00 AM");
        assertEquals(50, movie.getAvailableTickets());
        assertEquals(0, movie.getBookedTickets());
        assertTrue(output.contains("Not enough tickets available for this showtime"));
    }
    @Test
    void testCancelReservation_withValidTicketNumber_ReturnSuccessfullyCancelled() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("10:00 AM", 5);
        book.cancelReservation("10:00 AM", 3);
        String output = outContent.toString();
        Movie movie = book.getMovieByShowTime("10:00 AM");
        assertEquals(48, movie.getAvailableTickets());
        assertEquals(2, movie.getBookedTickets());
        assertTrue(output.contains("tickets successfully cancelled"));
    }
    @Test
    void testBookTickets_withDifferentShowTime_ReturnSuccessfulBooked() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("1:00 PM", 2);
        String output = outContent.toString();
        Movie movie = book.getMovieByShowTime("1:00 PM");
        assertEquals(48, movie.getAvailableTickets());
        assertEquals(2, movie.getBookedTickets());
        assertTrue(output.contains("tickets successfully booked"));
    }
    @Test
    void testCancelReservation_withInvalidShowTime_ReturnNoShowtime() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("1:00 PM", 2);
        book.cancelReservation("12:00 PM", 2);
        String output = outContent.toString();
        assertFalse(book.checkAvailability("12:00 PM"));
        assertTrue(output.contains("Showtime not found!")); 
    }
    @Test
    void testBookTickets_withInvalidShowTime_ReturnNoShowtime() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("12:00 PM", 2);
        String output = outContent.toString();
        assertFalse(book.checkAvailability("12:00 PM"));
        assertTrue(output.contains("Showtime not found!")); 
    }
    @Test
    void testBookingReservation_withMoreTicketsThanBooked_ReturnInvalidOperation() {
        MovieBookingSystem book = new MovieBookingSystem();
        book.bookTicket("1:00 PM", 2);
        book.cancelReservation("1:00 PM", 5);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid operation")); 
    }
    @Test
    void testMain() {
        MovieBookingSystem.main(null);
    }
}
