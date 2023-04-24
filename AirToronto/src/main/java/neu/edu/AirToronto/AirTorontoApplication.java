package neu.edu.AirToronto;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaEvent;
import com.netflix.discovery.EurekaEventListener;
import com.netflix.discovery.StatusChangeEvent;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AirTorontoApplication implements ApplicationRunner {
	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(AirTorontoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {

			System.out.println("application is running");
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> map = new HashMap<>();
			map.put("name","air-toronto");
			map.put("TEST","/hello2");
			map.put("VIEW_ALL_AIRLINES","/flight/all");
			String serverURL = "http://localhost:8081/";
			serverURL = serverURL + "portal/api/register";
			System.out.println(serverURL);

			String response = restTemplate.postForObject(serverURL, map, String.class);
			System.out.println(CommonUtils.success(response));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
