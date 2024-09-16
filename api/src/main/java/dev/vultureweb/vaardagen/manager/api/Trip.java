package dev.vultureweb.vaardagen.manager.api;

import java.time.LocalDate;

public record Trip(String id, String departurePort, String arrivalPort, LocalDate departureDate, LocalDate arrivalDate, int daysAtSea) {
    public Trip {
        if (departureDate.isAfter(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
        if(id.isBlank() || departurePort.isBlank() || arrivalPort.isBlank()) {
            throw new IllegalArgumentException("Id, departure port and destination port must not be blank");
        }
    }
}
