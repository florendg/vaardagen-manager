package dev.vultureweb.vaardagen.manager.simple.calculator;

import dev.vultureweb.vaardagen.manager.api.Trip;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    @Test
    void shouldAddOneDay() {
        var year= LocalDate.now().getYear() -2 ;
        var trip = new Trip("Amsterdam", "Rotterdam", LocalDate.of(year, 1, 1), LocalDate.of(year, 1, 2));
        var calculator = new SimpleCalculator();
        assertEquals(2, calculator.addTrip(trip));
        assertEquals(4, calculator.addTrip(trip));
    }

    @Test
    void shouldInitialiseToZero() {
        var calculator = new SimpleCalculator();
        assertEquals(0, calculator.currentNumberOfEligibleDaysAtSea());
    }

    @Test
    void shouldNotAddDaysOlderThanThreeYears() {
        var year= LocalDate.now().getYear() -3 ;
        var trip = new Trip("Amsterdam", "Rotterdam", LocalDate.of(year, 1, 1), LocalDate.of(year, 1, 2));
        var calculator = new SimpleCalculator();
        assertEquals(0, calculator.addTrip(trip));
        assertEquals(0, calculator.currentNumberOfEligibleDaysAtSea());
    }

}