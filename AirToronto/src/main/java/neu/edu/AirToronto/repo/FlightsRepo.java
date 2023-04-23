package neu.edu.AirToronto.repo;

import neu.edu.AirToronto.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
