package neu.edu.citycenter.services;

import neu.edu.citycenter.entities.Flight;
import neu.edu.citycenter.entities.FlightSearch;
import neu.edu.citycenter.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.regex.Pattern;

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

    public List<Flight> searchFlights(FlightSearch flightSearch, String country) {
        if (null != flightSearch.getCity() && !flightSearch.getCity().isBlank()) {
            String cityRegex = ".*" + Pattern.quote(flightSearch.getCity()) + ".*";
            return flightRepository.findByCityRegexAndCountry(cityRegex, country);
        } else {
            String iataRegex = ".*" + Pattern.quote(flightSearch.getAirport()) + ".*";
            return flightRepository.findByIataRegexAndCountry(iataRegex, country);
        }
    }
}

