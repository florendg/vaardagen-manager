package dev.vultureweb.vaardagen.manager.api;

import java.time.LocalDate;

public record Trip(String id, String departurePort, String arrivalPort, LocalDate departureDate, LocalDate arrivalDate, int daysAtSea) {

    private static final System.Logger LOG = System.getLogger(Trip.class.getName());
    public Trip {
        LOG.log(System.Logger.Level.INFO, "Creating trip" + this);
        if (departureDate.isAfter(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
        if(id.isBlank() || departurePort.isBlank() || arrivalPort.isBlank()) {
            throw new IllegalArgumentException("Id, departure port and destination port must not be blank");
        }
    }
}
