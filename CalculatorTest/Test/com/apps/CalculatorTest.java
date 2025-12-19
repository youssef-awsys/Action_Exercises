package com.apps;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {
    Calculator calculator = new Calculator();
    @BeforeAll
    static void setup() {
        System.out.println("This is the Before All");
    }
    @BeforeEach
    void setEach() {
        System.out.println("This is Before Each");
    }
    @DisplayName("Test 4/2 = 2")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        //fail("Not yet implemented");
        int result = calculator.integerDivision(4, 2);
        assertEquals(2,result, () -> "4" + "/" + "2" + "did not produce " + 2);
    }
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividentIsDividedByZero_ShouldThrowArithmeticException() {
        //fail("Not yet implemented");
        System.out.println("Running Division by Zero");
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        //Act and Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            //Act
            calculator.integerDivision(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception");
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");
    }
    
    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    @MethodSource
    void integerSubtraction(int minuend, int subtrahend, int expectedResult) {
        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult, () -> "minuend" + "/" + "subtrahend" + "did not process");
    }

    private static Stream<Arguments> integerSubtraction() {
        return Stream.of(
                Arguments.of(33, 1, 32),
                Arguments.of(24, 1, 23),
                Arguments.of(24, 1, 23)   
            );
    }
}
