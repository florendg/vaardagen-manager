package dev.vultureweb.vaardagen.manager.simple.calculator;

import java.time.LocalDate;

public record Entry(String id, LocalDate departureDate, int days) {
}
