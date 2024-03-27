package dev.vultureweb.vaardagen;

import dev.vultureweb.vaardagen.manager.api.Trip;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripTest {

  private final String expectedTripJson = """
      {
        "departurePort" : "departurePort",
        "destinationPort" : "destinationPort",
        "departureDate" : "2023-12-25",
        "arrivalDate" : "2023-12-25",
        "daysAtSea" : 0
      }""";

  @Test
  void tripToJson() throws Exception {
    Trip trip = new Trip("departurePort", "destinationPort", LocalDate.of(2023,12,25), LocalDate.of(2023,12,25),0);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));
    var json  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(trip);
    assertEquals(expectedTripJson, json);
    var mappedTrip = mapper.readValue(json, Trip.class);
    assertEquals(trip, mappedTrip);
  }
}
