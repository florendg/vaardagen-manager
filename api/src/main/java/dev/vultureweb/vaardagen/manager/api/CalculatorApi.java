package dev.vultureweb.vaardagen.manager.api;

import java.util.List;

public interface CalculatorApi {
    /**
     * Add a trip to the calculator
     * @param trip new trip to add
     * @return trip including the number of days at sea
     */
    Trip addTrip(Trip trip);

    /**
     * Retrieve all trips which count for the "Vaarbevoegdheid"
     * @return list of all trips
     */
    List<Trip> getTrips();

    /**
     * Retrieve the current number of eligible days at sea
     * @return number of days at sea which are eligible for the "Vaarbevoegdheid"
     */
    int currentNumberOfEligibleDaysAtSea();
}
