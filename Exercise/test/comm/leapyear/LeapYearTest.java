package comm.leapyear;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import comm.leapyear.LeapYear.CheckLeapYear;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LeapYearTest {
    @Order(1)
    @Test
    void testIsLeapYear_IfDivisibleBy400_true() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(2000);
        assertEquals(true, leapYear.isLeapYear());
        }
    @Order(2)
    @Test
    void testIsLeapYear_IfDisibleBy100ButNot400_false() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(1900);
        assertEquals(false, leapYear.isLeapYear());
        }
    @Order(3)
    @Test
    void testIsLeapYear_IfDisibleBy4ButNot100_true() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(2016);
        assertEquals(true, leapYear.isLeapYear());
        }
    @Order(4)
    @Test
    void testIsLeapYear_IfNotDisibleBy4_false() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(2017);
        assertEquals(false, leapYear.isLeapYear() );
        }
    @Order(5)
    @Test
    void testIsLeapYear_RandomLeapYear_true() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(1996);
        assertEquals(true, leapYear.isLeapYear());
        }
    @Order(6)
    @Test
    void testIsLeapYear_RandomNotLeapYear_false() {
        CheckLeapYear leapYear = new LeapYear.CheckLeapYear(1877);
        assertEquals(false, leapYear.isLeapYear());
        }
    @Test
    void testMain_ReturnVoid() {
        LeapYear leapYear = new LeapYear(2000);
        LeapYear.main(null);
    }
}
