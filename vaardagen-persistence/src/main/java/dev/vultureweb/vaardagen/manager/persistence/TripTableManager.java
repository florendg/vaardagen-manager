package dev.vultureweb.vaardagen.manager.persistence;

import dev.vultureweb.vaardagen.manager.api.Trip;
import jakarta.ejb.Stateless;
import jakarta.validation.constraints.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TripTableManager {

  private static final System.Logger LOG = System.getLogger(TripTableManager.class.getName());
  private static final String GET_QUERY = """
      SELECT trip_number,departure_harbour,departure_date,arrival_harbour,arrival_date FROM public.TRIP_LOG
      """;

  public List<Trip> getTrips(@NotNull Connection connection) {
    try {
      var statement = connection.prepareStatement(GET_QUERY);
      var resultSet = statement.executeQuery();
      var result = new ArrayList<Trip>();
      while (resultSet.next()) {
        result.add(new Trip(
            resultSet.getString("trip_number"),
            resultSet.getString("departure_harbour"),
            resultSet.getString("arrival_harbour"),
            resultSet.getDate("departure_date").toLocalDate(),
            resultSet.getDate("arrival_date").toLocalDate(),
            0
        ));
      }
      return result;
    } catch (Exception sqlException) {
      LOG.log(System.Logger.Level.ERROR,sqlException.getMessage());
    }
    return null;
  }

  public boolean addTrip(@NotNull Trip trip, Connection connection) {
    try {
      var statement = connection.prepareStatement("""
          INSERT INTO TRIP_LOG 
              VALUES (?, ?,?,?,?)
          """);
      statement.setInt(1, 0); // TODO
      statement.setString(1, trip.departurePort());
      statement.setDate(  2, Date.valueOf(trip.departureDate()));
      statement.setString(3, trip.arrivalPort());
      statement.setDate(  4, Date.valueOf(trip.arrivalDate()));
      return statement.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


}
