package dev.vultureweb.vaardagen.manager.api;

import java.time.LocalDate;

public record Trip(String uuid, String tripNumber, String departurePort, String arrivalPort,
                   LocalDate departureDate, LocalDate arrivalDate, int daysAtSea) {

    private static final System.Logger LOG = System.getLogger(Trip.class.getName());
    public Trip {
        if (departureDate.isAfter(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
        if(tripNumber.isBlank() || departurePort.isBlank() || arrivalPort.isBlank()) {
            throw new IllegalArgumentException("Id, departure port and destination port must not be blank");
        }
    }
}
