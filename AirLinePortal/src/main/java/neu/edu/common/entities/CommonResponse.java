package neu.edu.common.entities;

import lombok.Data;

/**
 * @author YUlia
 * @version 1.0
 */
@Data
public class CommonResponse {
    private String message = "";
    private boolean success;
    private Object body;

    public CommonResponse(boolean success) {
        this.success = success;
    }

    public CommonResponse(boolean success, Object body) {
        this.success = success;
        this.body = body;
    }

    public CommonResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
