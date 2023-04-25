package neu.edu.citycenter.repos;

import neu.edu.citycenter.entities.Flight;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */
public interface FlightRepository extends MongoRepository<Flight, ObjectId> {
    List<Flight> findByCityAndCountry(String city, String country);

    List<Flight> findByCountry(String country);

    List<Flight> findByCityRegexAndCountry(String cityRegex, String country);

    List<Flight> findByIataRegexAndCountry(String iataRegex, String country);

}

