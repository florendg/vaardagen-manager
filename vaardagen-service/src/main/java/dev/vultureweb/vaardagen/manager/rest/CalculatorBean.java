package dev.vultureweb.vaardagen.manager.rest;

import dev.vultureweb.vaardagen.manager.api.CalculatorApi;
import dev.vultureweb.vaardagen.manager.api.Trip;
import dev.vultureweb.vaardagen.manager.persistence.ConnectionProviderBean;
import dev.vultureweb.vaardagen.manager.persistence.ConnectionProviderBeanException;
import dev.vultureweb.vaardagen.manager.persistence.TripTableManager;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CalculatorBean implements CalculatorApi {

  @Inject
  ConnectionProviderBean connectionProviderBean;

  @PostConstruct
  public void loadData() {
  }

  @Override
  public Trip addTrip(Trip trip) {
    try (var connection = connectionProviderBean.connect()) {
      new TripTableManager().addTrip(trip, connection);
      return trip;
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public List<Trip> getTrips() {
    try (var connection = connectionProviderBean.connect()) {
      return new TripTableManager().getTrips(connection);
    } catch (ConnectionProviderBeanException | SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public int currentNumberOfEligibleDaysAtSea() {
    throw new UnsupportedOperationException("CurrentNumberOfEligibleDaysAtSea() not implemented");
  }
}
