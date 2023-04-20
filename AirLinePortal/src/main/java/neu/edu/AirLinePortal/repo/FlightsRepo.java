package neu.edu.AirLinePortal.repo;

import neu.edu.AirLinePortal.entities.Flight;
import neu.edu.AirLinePortal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author YUlia
 * @version 1.0
 */

@Repository
public interface FlightsRepo extends JpaRepository<Flight, String> {
    @Query(value = "select * from flightsInfo where date = ?1 and flight_number = ?2", nativeQuery = true)
    Flight findByDateAndNo(String date, String flightNo);
}
