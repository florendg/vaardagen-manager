package dev.vultureweb.vaardagen.manager.rest;

import dev.vultureweb.vaardagen.manager.api.CalculatorApi;
import dev.vultureweb.vaardagen.manager.api.Trip;
import dev.vultureweb.vaardagen.manager.simple.calculator.SimpleCalculator;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculatorBean implements CalculatorApi {

  private final SimpleCalculator simpleCalculator = new SimpleCalculator();

  @PostConstruct
  public void loadData() {}

  @Override
  public int addTrip(Trip trip) {
    return simpleCalculator.addTrip(trip);
  }

  @Override
  public int currentNumberOfEligibleDaysAtSea() {
    return simpleCalculator.currentNumberOfEligibleDaysAtSea();
  }
}
