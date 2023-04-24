package neu.edu.AirLinePortal.controller;

import neu.edu.AirLinePortal.api.ApiCenter;
import neu.edu.AirLinePortal.service.CallAPIs;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
}
