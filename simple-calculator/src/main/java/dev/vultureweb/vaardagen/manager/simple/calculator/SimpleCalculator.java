package dev.vultureweb.vaardagen.manager.simple.calculator;

import dev.vultureweb.vaardagen.manager.api.CalculatorApi;
import dev.vultureweb.vaardagen.manager.api.Trip;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class SimpleCalculator implements CalculatorApi {

    private static final System.Logger LOG = System.getLogger(SimpleCalculator.class.getName());

    private final Supplier<LocalDate> cutOffDate = () -> LocalDate.now().minusYears(3);

    private final List<Entry> store = new ArrayList<>();

    @Override
    public int addTrip(Trip trip) {
        LOG.log(System.Logger.Level.INFO, "Adding trip: {0}", trip);
        var days = Period.between(trip.departureDate(), trip.arrivalDate()).getDays() + 1;
        store.add(new Entry(trip.departureDate(), days));
        return store.stream()
                .filter(entry -> entry.departureDate().isAfter(cutOffDate.get()))
                .mapToInt(Entry::days).sum();
    }

    @Override
    public int currentNumberOfEligibleDaysAtSea() {
        return store.stream()
                .filter(entry -> entry.departureDate().isAfter(cutOffDate.get()))
                .mapToInt(Entry::days).sum();
    }
}
