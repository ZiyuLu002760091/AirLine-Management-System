package neu.edu.AirToronto.controller;

import jakarta.validation.Valid;
import neu.edu.AirToronto.entities.Flight;
import neu.edu.AirToronto.service.FlightsInfoService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class FlightsInfoController {
    @Autowired
    private FlightsInfoService flightsInfoService;

    @GetMapping(path = "/flight/all", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> findAllFlights() {
        return ResponseEntity.ok(CommonUtils.success(flightsInfoService.findAllFlights()));
    }

    @PostMapping(path = "/flight/create", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createFlight(@RequestBody @Valid Flight flight, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if(!validateSuccessful) {
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            flightsInfoService.createFlight(flight);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

    @PostMapping(path = "/flight/update", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> updateFlightActTime(@RequestBody @Valid Flight flight, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if(!validateSuccessful) {
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            flightsInfoService.updateActDatetime(flight);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

    @PostMapping(path = "/flight/delete", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> deleteFlightActTime(@RequestBody @Valid Flight flight, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if(!validateSuccessful) {
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            flightsInfoService.deleteFlight(flight);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

    @GetMapping("/flights/search")
    public ResponseEntity<String> searchFlights(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        try {
            List<Flight> flights = flightsInfoService.searchFlights(from, to, date);
            return ResponseEntity.ok(CommonUtils.success(flights));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
    }
}
