package neu.edu.AirLinePortal.controller;

import jakarta.validation.Valid;
import neu.edu.AirLinePortal.entities.Flight;
import neu.edu.AirLinePortal.service.FlightsInfoService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/flight/create", produces = "application/json")
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
}
