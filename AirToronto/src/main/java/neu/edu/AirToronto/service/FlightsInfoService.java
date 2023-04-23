package neu.edu.AirToronto.service;

import neu.edu.AirToronto.entities.Flight;
import neu.edu.AirToronto.repo.FlightsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        //do the validation here
        if(null != flight) {
            if(null == flightsRepo.findByDateAndNo(flight.getEstimated_departure_datetime(), flight.getFlight_number())) {
                //no such flight
                Flight jpaFlight = new Flight();
                jpaFlight.setUuid(flight.getUuid());
                jpaFlight.setFlight_number(flight.getFlight_number());
                jpaFlight.setCompany_name(flight.getCompany_name());
                jpaFlight.setEstimated_departure_datetime(flight.getEstimated_departure_datetime());
                jpaFlight.setEstimated_arrival_datetime(flight.getEstimated_arrival_datetime());
                jpaFlight.setOrigin(flight.getOrigin());
                jpaFlight.setDestination(flight.getDestination());
                jpaFlight.setStatus(flight.getStatus());

                flightsRepo.save(jpaFlight);
//                jpaFlight.setActual_departure_datetime(flight.getActual_departure_datetime());
//                jpaFlight.setActual_arrival_datetime();
            }
        }
    }

    public void updateActDatetime(Flight flight) throws Exception {
        if(null != flight) {
            Optional<Flight> jpaFlight = flightsRepo.findById(flight.getUuid());

            if(jpaFlight.isPresent()) {
//                flightsRepo.updateFlight(flight.getActual_departure_datetime(),flight.getActual_arrival_datetime(), flight.getUuid());
                jpaFlight.get().setActual_arrival_datetime(flight.getActual_arrival_datetime());
                jpaFlight.get().setActual_departure_datetime(flight.getActual_departure_datetime());
                flightsRepo.save(jpaFlight.get());
            } else {
                throw new RuntimeException("no such flight");
            }
        }
    }

    public void deleteFlight(Flight flight) throws Exception{
        if(null != flight) {
            if(flightsRepo.findById(flight.getUuid()).isPresent()) {
                flightsRepo.deleteById(flight.getUuid());
            }
        }
    }



    public Flight findFlightByDateAndFlightNo(String date,String flightNo) {
        return flightsRepo.findByDateAndNo(date, flightNo);
    }


}
