package neu.edu.AirLinePortal.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import neu.edu.AirLinePortal.api.ApiCenter;
import neu.edu.AirLinePortal.service.CallAPIs;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private CallAPIs callAPIs;

    @GetMapping(path = "/helloAT")
    public ResponseEntity<String> helloAT() {
        Map map = new HashMap<>();
        String serverURL = eurekaClient.getNextServerFromEureka("air-toronto", false).getHomePageUrl();
        serverURL = serverURL + "air-toronto/hello2";
        System.out.println(serverURL);
//        ResponseEntity<CommonResponse> response = restTemplate.getForEntity(serverURL, CommonResponse.class, map);
        ResponseEntity<String> response = restTemplate.getForEntity(serverURL, String.class, map);
        return ResponseEntity.ok(CommonUtils.success(response.getBody()));
    }


    @GetMapping(path = "/helloAll")
    public ResponseEntity<String> helloAll() {
        Map<String, CommonResponse> resp = callAPIs.getAPIs(ApiCenter.TEST, new HashMap<>());
        return ResponseEntity.ok(CommonUtils.success(resp));
    }
}
