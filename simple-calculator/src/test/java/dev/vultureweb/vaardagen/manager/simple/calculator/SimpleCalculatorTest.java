package dev.vultureweb.vaardagen.manager.simple.calculator;

import dev.vultureweb.vaardagen.manager.api.Trip;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    @Test
    void shouldAddOneDay() {
        var year= LocalDate.now().getYear() -2 ;
        var trip = new Trip("Amsterdam", "Rotterdam", LocalDate.of(year, 1, 1), LocalDate.of(year, 1, 2),0);
        var calculator = new SimpleCalculator();
        calculator.addTrip(trip);
        assertEquals(2, calculator.currentNumberOfEligibleDaysAtSea());
        calculator.addTrip(trip);
        assertEquals(4, calculator.currentNumberOfEligibleDaysAtSea());
    }

    @Test
    void shouldInitialiseToZero() {
        var calculator = new SimpleCalculator();
        assertEquals(0, calculator.currentNumberOfEligibleDaysAtSea());
    }

    @Test
    void shouldNotAddDaysOlderThanThreeYears() {
        var year= LocalDate.now().getYear() -3 ;
        var trip = new Trip("Amsterdam", "Rotterdam", LocalDate.of(year, 1, 1), LocalDate.of(year, 1, 2),0);
        var calculator = new SimpleCalculator();
        assertEquals(0, calculator.currentNumberOfEligibleDaysAtSea());
        calculator.addTrip(trip);
        assertEquals(0, calculator.currentNumberOfEligibleDaysAtSea());
    }

}