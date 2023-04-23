package neu.edu.AirLinePortal.controller;

import neu.edu.AirLinePortal.service.CallAPIs;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class ApiController {
    @Autowired
    private CallAPIs callAPIs;

    @PostMapping(path = "/api", produces = "application/json")
    public ResponseEntity<String> registerAPI(@RequestBody HashMap<String, Object> parameter) {
        callAPIs.registerAPIs(parameter);
        return ResponseEntity.ok(CommonUtils.success(parameter));
    }
}
