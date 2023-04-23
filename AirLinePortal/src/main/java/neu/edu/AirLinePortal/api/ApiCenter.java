package neu.edu.AirLinePortal.api;

import java.util.*;

/**
 * @author YUlia
 * @version 1.0
 */
public class ApiCenter {
    public static final String TEST = "TEST";

    public static final String VIEW_ALL_AIRLINES = "VIEW_ALL_AIRLINES";
    public static final String VIEW_CERTAIN_AIRLINES = "VIEW_CERTAIN_AIRLINES";
    public static final String CREATE_AIRLINE = "CREATE_AIRLINE";
    public static final String UPDATE_AIRLINE = "UPDATE_AIRLINE";
    public static final String DELETE_AIRLINE = "DELETE_AIRLINE";
    private static ApiCenter instance = new ApiCenter();
    private ApiCenter() {

    }
    public static ApiCenter getInstance() {
        return instance;
    }

    private HashMap<String, Map<String,Object>> map = new HashMap<>();

    public void register(HashMap<String, Object> hashMap) {
        String serverName = hashMap.get("name").toString();
        hashMap.remove("name");
        map.put(serverName,hashMap);
    }

    public Map<String,String> getApis(String issue) {
        Map<String,String> paths = new HashMap<>();
        Set<String> servers = map.keySet();
        for (String server : servers) {
            Map<String,Object> services = map.get(server);
            if(services.containsKey(issue)) {
                paths.put(server, services.get(issue).toString());
            }
        }
        return paths;
    }
}
