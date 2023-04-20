package neu.edu.AirLinePortal.service;

import neu.edu.AirLinePortal.entities.Flight;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.repo.FlightsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class FlightsInfoService {
    @Autowired
    private FlightsRepo flightsRepo;

    public List<Flight> findAllFlights() {
        return flightsRepo.findAll();
    }

    public void createFlight(Flight flight) throws Exception {
        flightsRepo.save(flight);
    }

    public Flight findFlightByDateAndFlightNo(String date,String flightNo) {
        return flightsRepo.findByDateAndNo(date, flightNo);
    }


}
