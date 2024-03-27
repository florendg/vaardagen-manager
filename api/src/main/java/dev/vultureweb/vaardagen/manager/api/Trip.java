package dev.vultureweb.vaardagen.manager.api;

import java.time.LocalDate;

public record Trip(String departurePort, String destinationPort, LocalDate departureDate, LocalDate arrivalDate,int daysAtSea) {
    public Trip {
        if (departureDate.isAfter(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
    }
}
