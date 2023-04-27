package neu.edu.AirLinePortal.service;

import neu.edu.AirLinePortal.api.ApiCenter;
import neu.edu.AirLinePortal.entities.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class ApiCompanyService {


    public List<CompanyInfo> getAllCompanies() {
        HashMap<String, Map<String, Object>> map = ApiCenter.getInstance().getMap();
        List<CompanyInfo> companies = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            String companyName = entry.getKey();
            Map<String, Object> companyApis = entry.getValue();

            for (Map.Entry<String, Object> apiEntry : companyApis.entrySet()) {
                String apiName = apiEntry.getKey();
                Object apiPath = apiEntry.getValue();

                CompanyInfo companyInfo = new CompanyInfo(companyName, apiName, apiPath.toString());
                companies.add(companyInfo);
            }
        }

        return companies;
    }
}

