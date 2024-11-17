package dev.vultureweb.vaardagen;

import dev.vultureweb.vaardagen.manager.api.Trip;
import dev.vultureweb.vaardagen.manager.rest.JacksonContextResolver;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripTest {

  private final String expectedTripJson = """
      {
        "id" : "231225",
        "departurePort" : "departurePort",
        "arrivalPort" : "arrivalPort",
        "departureDate" : "2023-12-25",
        "arrivalDate" : "2023-12-25",
        "daysAtSea" : 0
      }""";

  @Test
  void tripToJson() throws Exception {
    Trip trip = new Trip("231225", "departurePort", "arrivalPort", LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 25), 0);
    var JacksonContextResolver = new JacksonContextResolver();
    var mapper = JacksonContextResolver.getContext(ObjectMapper.class);
    var json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(trip);
    assertEquals(expectedTripJson, json);
    var mappedTrip = mapper.readValue(json, Trip.class);
    assertEquals(trip, mappedTrip);
  }

}
