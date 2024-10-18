package dev.vultureweb.vaardagen;

import dev.vultureweb.vaardagen.manager.api.Trip;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;


class ApiTest {

  @Test
  void shouldGetTrips() {
    when().get("http://localhost:8080/calculator-service/vaardagen/trips")
        .then()
        .statusCode(200)
        .body("", hasSize(38));
  }

  @Test
  void shouldBeAbleToAddATrip() {

    var trip = """
        {
        "id": 666,
        "departureDate": "01-11-2024",
        "departurePort": "vertrekPunt",
        "arrivalDate": "02-02-2025",
        "arrivalPort": "aankomstPlaats"
        }
        """;
    given()
        .contentType(ContentType.JSON)
        .body(trip)
        .when()
        .post("http://localhost:8080/calculator-service/vaardagen/trip")
        .then()
        .statusCode(200);
  }
}