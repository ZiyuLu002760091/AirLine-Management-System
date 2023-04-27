package neu.edu.AirLinePortal.controller;

import neu.edu.AirLinePortal.entities.CompanyInfo;
import neu.edu.AirLinePortal.service.ApiCompanyService;
import neu.edu.AirLinePortal.service.CallAPIs;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
public class ApiController {
    @Autowired
    private CallAPIs callAPIs;

    @Autowired
    private ApiCompanyService companyService;

    @GetMapping(path = "/api/companies", produces = "application/json")
    public ResponseEntity<String> getAllCompanies() {
        List<CompanyInfo> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(CommonUtils.success(companies));
    }

    @PostMapping(path = "/api/register", produces = "application/json")
    public ResponseEntity<String> registerAPI(@RequestBody HashMap<String, Object> parameter) {
        callAPIs.registerAPIs(parameter);
        return ResponseEntity.ok(CommonUtils.success(parameter));
    }


    @GetMapping(path = "/api/all/{api}", produces = "application/json")
    public ResponseEntity<String> delegateGet(@PathVariable String api, @RequestBody HashMap<String, Object> parameter) {
        Map<String, CommonResponse> resp = callAPIs.getAPIs(api, parameter);
        return ResponseEntity.ok(CommonUtils.success(resp));
    }

    @GetMapping(path = "/api/specific/{server}/{api}", produces = "application/json")
    public ResponseEntity<String> delegateGetOne(@PathVariable String server,@PathVariable String api, @RequestBody HashMap<String, Object> parameter) {
        CommonResponse resp = callAPIs.getSpecificAPI(api, server, parameter);
        return ResponseEntity.ok(CommonUtils.success(resp));
    }

    @PostMapping(path = "/api/specific/{server}/{api}", produces = "application/json")
    public ResponseEntity<String> delegatePostOne(@PathVariable String server,@PathVariable String api, @RequestBody HashMap<String, Object> parameter) {
        CommonResponse resp = callAPIs.postSpecificAPI(api, server, parameter);
        return ResponseEntity.ok(CommonUtils.success(resp));
    }
}
