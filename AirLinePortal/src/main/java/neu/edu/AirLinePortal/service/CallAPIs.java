package neu.edu.AirLinePortal.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.netflix.discovery.EurekaClient;
import neu.edu.AirLinePortal.api.ApiCenter;
import neu.edu.common.entities.CommonResponse;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class CallAPIs {
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    public boolean registerAPIs(HashMap<String, Object> hashMap) {
        ApiCenter.getInstance().register(hashMap);
        return true;
    }

    public Map<String,CommonResponse> getAPIs(String issue,HashMap<String, Object> hashMap) {
        Map<String, String> apis = ApiCenter.getInstance().getApis(issue);
        Map<String,CommonResponse> responseMap = new HashMap<>();
        for (String server:
             apis.keySet()) {
            String serverURL = eurekaClient.getNextServerFromEureka(server, false).getHomePageUrl();
            serverURL = serverURL + server + apis.get(server);
            System.out.println(serverURL);
            ResponseEntity<String> response = restTemplate.getForEntity(serverURL, String.class, hashMap);
            String body = response.getBody();
            System.out.println(body);
            Gson jsonObject = new Gson();
            CommonResponse jsonBody = jsonObject.fromJson(body, CommonResponse.class);
            responseMap.put(server,jsonBody);
        }
        return responseMap;

//        ResponseEntity<CommonResponse> response = restTemplate.getForEntity(serverURL, CommonResponse.class, map);

    }
}
