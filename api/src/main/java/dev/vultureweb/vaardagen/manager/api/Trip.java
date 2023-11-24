package dev.vultureweb.vaardagen.manager.api;

import java.time.LocalDate;

public record Trip(String departurePort, String destinationPort, LocalDate departureDate, LocalDate arrivalDate) {
}
