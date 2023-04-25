package neu.edu.AirLinePortal.controller;

import neu.edu.AirLinePortal.api.ApiCenter;
import neu.edu.AirLinePortal.service.CallAPIs;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/apis")
public class DelegateController {
    @Autowired
    private CallAPIs callAPIs;


    @GetMapping(path = "/airlines/all",produces = "application/json")
    public ResponseEntity<String> getAllFlights() {
        Map<String, CommonResponse> responseMap = callAPIs.getAPIs(ApiCenter.VIEW_ALL_AIRLINES, new HashMap<>());
        return ResponseEntity.ok(CommonUtils.success(responseMap));
    }


    @GetMapping(path = "/airlines/search", produces = "application/json")
    public ResponseEntity<String> searchFlights(@RequestParam Map<String, Object> map) {
        Map<String, CommonResponse> responseMap = callAPIs.getAPIs(ApiCenter.SEARCH_AIRLINE, map);
        return ResponseEntity.ok(CommonUtils.success(responseMap));
    }
}
