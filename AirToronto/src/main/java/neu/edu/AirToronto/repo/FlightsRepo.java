package neu.edu.AirToronto.repo;

import neu.edu.AirToronto.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */

@Repository
public interface FlightsRepo extends JpaRepository<Flight, String> {
    @Query(value = "select * from flightsInfo where date(estimated_departure_datetime) = ?1 and flight_number = ?2", nativeQuery = true)
    Flight findByDateAndNo(String date, String flightNo);

    @Query(value = "update flightsinfo set actual_departure_datetime = ?1, actual_arrival_datetime = ?2 where uuid = ?3", nativeQuery = true)
    Flight updateFlight(String ActDeDatetime, String ActArDatetime, String id);

    @Query(value = "SELECT * FROM flightsinfo WHERE origin = ?1 AND destination = ?2 AND DATE(estimated_departure_datetime) = ?3 AND status = 'planning' ORDER BY price, estimated_departure_datetime", nativeQuery = true)
    List<Flight> findFlightsByOriginAndDestinationAndDate(String origin, String destination, String date);

    @Query(value = """
    SELECT * FROM flightsinfo
    WHERE origin = ?1
    AND destination = ?2
    AND DATE(estimated_departure_datetime) = ?3
    """, nativeQuery = true)
    List<Flight> findFlightsByAirportsAndDate(String from, String to, LocalDate date);
}
