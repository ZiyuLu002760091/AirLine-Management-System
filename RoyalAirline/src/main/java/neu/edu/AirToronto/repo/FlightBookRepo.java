package neu.edu.AirToronto.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import neu.edu.AirToronto.entities.FlightBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */
@Repository
public class FlightBookRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<FlightBook> getFlightReservations(String userid) {
        String sql = "SELECT f.uuid, f.flight_number, f.company_name, f.estimated_departure_datetime, f.estimated_arrival_datetime,"
                + " f.origin, f.destination, f.status, f.actual_departure_datetime, f.actual_arrival_datetime,"
                + " f.price, f.member_discount, b.userid, b.date, b.server, b.book_status"
                + " FROM flightsinfo f"
                + " JOIN book b ON f.uuid = b.flightid"
                + " WHERE b.userid = '" + userid + "'";

        Query query = entityManager.createNativeQuery(sql, FlightBook.class);

        List<FlightBook> flightReservations = query.getResultList();

        return flightReservations;
    }

}
