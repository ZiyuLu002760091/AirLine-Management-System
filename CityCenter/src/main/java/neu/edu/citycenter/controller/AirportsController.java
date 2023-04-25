package neu.edu.citycenter.controller;

import neu.edu.citycenter.entities.FlightSearch;
import neu.edu.citycenter.services.FlightService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
@CrossOrigin("*")
public class AirportsController {

    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/airports/all", produces = "application/json")
    public ResponseEntity<String> getAllAirportsInCanada() {
        return ResponseEntity.ok(CommonUtils.success(flightService.getFlightsByCountry("CA")));
    }

    @PostMapping(path = "/airports/search", produces = "application/json")
    public ResponseEntity<String> searchAirportsInCanada(@RequestBody FlightSearch flightSearch) {
        return ResponseEntity.ok(CommonUtils.success(flightService.searchFlights(flightSearch, "CA")));
    }
}
