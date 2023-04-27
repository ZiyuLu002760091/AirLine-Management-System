package neu.edu.AirLinePortal.entities;

/**
 * @author YUlia
 * @version 1.0
 */
public class CompanyInfo {

    private String companyName;
    private String apiName;
    private String apiPath;

    public CompanyInfo(String companyName, String apiName, String apiPath) {
        this.companyName = companyName;
        this.apiName = apiName;
        this.apiPath = apiPath;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "companyName='" + companyName + '\'' +
                ", apiName='" + apiName + '\'' +
                ", apiPath='" + apiPath + '\'' +
                '}';
    }
}

