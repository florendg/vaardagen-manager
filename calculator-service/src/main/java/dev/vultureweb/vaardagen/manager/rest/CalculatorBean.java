package dev.vultureweb.vaardagen.manager.rest;

import dev.vultureweb.vaardagen.manager.api.CalculatorApi;
import dev.vultureweb.vaardagen.manager.api.Trip;
import dev.vultureweb.vaardagen.manager.persistence.ConnectionProviderBean;
import dev.vultureweb.vaardagen.manager.persistence.TripTableManager;
import dev.vultureweb.vaardagen.manager.simple.calculator.SimpleCalculator;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CalculatorBean implements CalculatorApi {

  private final SimpleCalculator simpleCalculator = new SimpleCalculator();

  @Inject
  ConnectionProviderBean connectionProviderBean;

  @PostConstruct
  public void loadData() {}

  @Override
  public Trip addTrip(Trip trip) {
    return simpleCalculator.addTrip(trip);
  }

  @Override
  public List<Trip> getTrips() {
    try(var connection = connectionProviderBean.connect()) {
      return new TripTableManager().getTrips(connection);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public int currentNumberOfEligibleDaysAtSea() {
    return simpleCalculator.currentNumberOfEligibleDaysAtSea();
  }
}
