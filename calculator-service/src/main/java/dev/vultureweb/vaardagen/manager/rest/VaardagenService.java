package dev.vultureweb.vaardagen.manager.rest;

import dev.vultureweb.vaardagen.manager.api.CalculatorApi;
import dev.vultureweb.vaardagen.manager.api.Trip;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@Path("/vaardagen")
public class VaardagenService implements CalculatorApi {

  private static final System.Logger LOG = System.getLogger(VaardagenService.class.getName());

  @Inject
  private CalculatorBean calculatorBean;

  @Override
  @POST
  @Path("/trip")
  @Consumes("application/json")
  @Produces("application/json")
  public int addTrip(Trip trip) {
    LOG.log(System.Logger.Level.INFO, "addTrip: " + trip);
    return calculatorBean.addTrip(trip);
  }

  @Override
  @GET
  @Produces("application/json")
  public int currentNumberOfEligibleDaysAtSea() {
    return calculatorBean.currentNumberOfEligibleDaysAtSea();
  }
}
