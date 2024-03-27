package dev.vultureweb.vaardagen.manager.api;

public interface CalculatorApi {
    /**
     * Add a trip to the calculator
     * @param trip new trip to add
     * @return trip including the number of days at sea
     */
    Trip addTrip(Trip trip);

    /**
     * Retrieve the current number of eligible days at sea
     * @return number of days at sea which are eligible for the "Vaarbevoegdheid"
     */
    int currentNumberOfEligibleDaysAtSea();
}
