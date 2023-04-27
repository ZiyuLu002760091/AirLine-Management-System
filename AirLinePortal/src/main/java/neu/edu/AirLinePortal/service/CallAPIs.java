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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * API Delegate
 * "important class"
 *
 * @see ApiCenter for storing all the registered APIs
 * @author YUlia
 * @version 1.0
 */
@Service
public class CallAPIs {
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * This method is for other sub systems registering services
     * <p>
     * HashMap format:
     * <p>
     * <code>
     * {
     * 	"name":"air-toronto",
     * 	"VIEW_ALL_AIRLINES":"/airlines/all",
     * 	"VIEW_CERTAIN_AIRLINES":"/airline"
     * }
     * </code>
     * The server name, provided services and their paths would be extracted from the above JSON (HashMap)
     */
    public boolean registerAPIs(HashMap<String, Object> hashMap) {
        ApiCenter.getInstance().register(hashMap);
        return true;
    }

    /**
     * The method is to resend the API to the sub systems
     * {@code registerAPIs} has to be called first so that the corresponding sub system services will be called
     * HashMap here is params, will not extract specific keys in this method, this is just for taking all and resend
     * (There may be some common keys that can be extracted in the future to adjust the feature of this method,
     * for now this is not implemented)
     *
     * For now this method do "GET" only.
     *
     * @param issue see the APIs in {@code ApiCenter}
     * @param hashMap HashMap here is params, will not extract specific keys in this method, this is just for taking all and resend
     */
    public Map<String, CommonResponse> getAPIs(String issue, Map<String, Object> hashMap) {
        System.out.println("receive api calls for " + issue + "with params");
        if (null != hashMap) {
            hashMap.forEach((k, v ) -> {
                System.out.println("key: " + k + " value " + v);
            });
        }
        Map<String, String> apis = ApiCenter.getInstance().getApis(issue);
        Map<String, CommonResponse> responseMap = new HashMap<>();
        for (String server :
                apis.keySet()) {
            String serverURL = eurekaClient.getNextServerFromEureka(server, false).getHomePageUrl();
            serverURL = serverURL + server + apis.get(server);
            System.out.println(serverURL);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverURL);

            if (null != hashMap) {
                hashMap.forEach(builder::queryParam);
            }

            String url = builder.toUriString();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

//            ResponseEntity<String> response = restTemplate.getForEntity(serverURL, String.class, hashMap);
            String body = response.getBody();
            System.out.println(body);
            Gson jsonObject = new Gson();
            CommonResponse jsonBody = jsonObject.fromJson(body, CommonResponse.class);
            responseMap.put(server, jsonBody);
        }
        return responseMap;

//        ResponseEntity<CommonResponse> response = restTemplate.getForEntity(serverURL, CommonResponse.class, map);
    }

    public CommonResponse getSpecificAPI(String issue, String server, Map<String, Object> hashMap) {
        String apiPath = ApiCenter.getInstance().getAPI(issue, server);
        String serverURL = eurekaClient.getNextServerFromEureka(server, false).getHomePageUrl();
        serverURL = serverURL + server + apiPath;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverURL);

        if (null != hashMap) {
            hashMap.forEach(builder::queryParam);
        }

        String url = builder.toUriString();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

//            ResponseEntity<String> response = restTemplate.getForEntity(serverURL, String.class, hashMap);
        String body = response.getBody();
        System.out.println(body);
        Gson jsonObject = new Gson();
        CommonResponse jsonBody = jsonObject.fromJson(body, CommonResponse.class);

        return jsonBody;
    }


    public CommonResponse postSpecificAPI(String issue, String server, Map<String, Object> hashMap) {
        String apiPath = ApiCenter.getInstance().getAPI(issue, server);
        String serverURL = eurekaClient.getNextServerFromEureka(server, false).getHomePageUrl();
        serverURL = serverURL + server + apiPath;

        ResponseEntity<String> response = restTemplate.postForEntity(serverURL, hashMap, String.class);

//            ResponseEntity<String> response = restTemplate.getForEntity(serverURL, String.class, hashMap);
        String body = response.getBody();
        System.out.println(body);
        Gson jsonObject = new Gson();
        CommonResponse jsonBody = jsonObject.fromJson(body, CommonResponse.class);

        return jsonBody;
    }
}
