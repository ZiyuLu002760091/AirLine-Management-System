package neu.edu.citycenter.services;

import neu.edu.citycenter.entities.Flight;
import neu.edu.citycenter.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlightsByCityAndCountry(String city, String country) {
        return flightRepository.findByCityAndCountry(city, country);
    }

    public List<Flight> getFlightsByCountry(String country) {
        return flightRepository.findByCountry(country);
    }
}

