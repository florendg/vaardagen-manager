package dev.vultureweb.vaardagen.manager.persistence;

import dev.vultureweb.vaardagen.manager.api.Trip;
import jakarta.validation.constraints.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripTableManager {

  private static final System.Logger LOG = System.getLogger(TripTableManager.class.getName());

  private static final String GET_QUERY = """
      SELECT id, trip_number,departure_harbour,departure_date,arrival_harbour,arrival_date, days_at_sea FROM public.TRIP_LOG
      WHERE arrival_date >= CURRENT_DATE - INTERVAL '5 years'
      """;

  public List<Trip> getTrips(@NotNull Connection connection) {
    try {
      var statement = connection.prepareStatement(GET_QUERY);
      var resultSet = statement.executeQuery();
      var result = new ArrayList<Trip>();
      while (resultSet.next()) {
        result.add(new Trip(
            resultSet.getString("id"),
            resultSet.getString("trip_number"),
            resultSet.getString("departure_harbour"),
            resultSet.getString("arrival_harbour"),
            resultSet.getDate("departure_date").toLocalDate(),
            resultSet.getDate("arrival_date").toLocalDate(),
            resultSet.getInt("days_at_sea")
        ));
      }
      return result;
    } catch (Exception sqlException) {
      LOG.log(System.Logger.Level.ERROR,sqlException.getMessage());
    }
    return null;
  }

  public void addTrip(@NotNull Trip trip, Connection connection) {
    try {
      var statement = connection.prepareStatement("""
          INSERT INTO TRIP_LOG ("trip_number","departure_harbour", "departure_date", "arrival_harbour", "arrival_date", "days_at_sea") 
              VALUES (?, ?,?,?,?, ?)
          """);
      statement.setInt(1, Integer.parseInt(trip.tripNumber()));
      statement.setString(2, trip.departurePort());
      statement.setDate(  3, Date.valueOf(trip.departureDate()));
      statement.setString(4, trip.arrivalPort());
      statement.setDate(  5, Date.valueOf(trip.arrivalDate()));
      statement.setInt(6, trip.daysAtSea());

      var _result =  statement.execute();
    } catch (SQLException e) {
      LOG.log(System.Logger.Level.ERROR,e.getMessage());
      throw new TripTableManagerException(e);
    }
  }


}
