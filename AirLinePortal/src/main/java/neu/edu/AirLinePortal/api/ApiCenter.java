package neu.edu.AirLinePortal.api;

import java.util.*;

/**
 * @author YUlia
 * @version 1.0
 */
public class ApiCenter {
    public static final String TEST = "TEST";

    /**
     * Those strings are defining the standard behaviors or services the sub systems should provide
     *The subsystem should call {@code ApiController} to register their servername and service (API) paths in the ApiCenter.
     * Then the portal can call the subsystems' APIs based on the paths they gave.
     *
     * @see neu.edu.AirLinePortal.controller.ApiController
     * VIEW_ALL_AIRLINES: List<String> to get all airlines from all companies
     * VIEW_CERTAIN_AIRLINES: <String> to view certain company's airlines
     * CREATE_AIRLINE: to create airline in a certain company
     * UPDATE_AIRLINE: to update one airline info in a certain company
     * DELETE_AIRLINE: to delete one airline info in a certain company
     */
    public static final String VIEW_ALL_AIRLINES = "VIEW_ALL_AIRLINES";
    public static final String VIEW_CERTAIN_AIRLINES = "VIEW_CERTAIN_AIRLINES";
    public static final String CREATE_AIRLINE = "CREATE_AIRLINE";
    public static final String UPDATE_AIRLINE = "UPDATE_AIRLINE";
    public static final String DELETE_AIRLINE = "DELETE_AIRLINE";
    public static final String SEARCH_AIRLINE = "SEARCH_AIRLINE";

    private static ApiCenter instance = new ApiCenter();
    private ApiCenter() {

    }
    public static ApiCenter getInstance() {
        return instance;
    }

    /**
     * The data format stored inside this HashMap:
     *
     * <code>
         {
             "air-toronto": {
                 "VIEW_ALL_AIRLINES":"/airlines/all",
                 "VIEW_CERTAIN_AIRLINES":"/airline"
             },
             "northeastern-airline": {
                 "VIEW_ALL_AIRLINES":"/airlines/all",
                 "VIEW_CERTAIN_AIRLINES":"/airline"
             },
             "xxx-airline": {
                 "service1": "path1",
                 "service2": "path2",
                 ......
             }
         }
     * </code>
     */
    private HashMap<String, Map<String,Object>> map = new HashMap<>();

    public void register(HashMap<String, Object> hashMap) {
        String serverName = hashMap.get("name").toString();
        hashMap.remove("name");
        map.put(serverName,hashMap);
    }

    /**
     * This is to get a particular service from all the sub systems, so that they can be called in uniformly.
     */
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

    public HashMap<String, Map<String, Object>> getMap() {
        return map;
    }
}
