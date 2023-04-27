package neu.edu.common.utils;

import com.google.gson.Gson;
import neu.edu.common.entities.CommonResponse;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YUlia
 * @version 1.0
 */
public class CommonUtils {

    public static String success() {
        return new Gson().toJson(new CommonResponse(true));
    }

    public static String success(Object body) {
        CommonResponse resp = new CommonResponse(true, body);
        return new Gson().toJson(resp);
    }

    public static String failed(String errorMsg) {
        return new Gson().toJson(new CommonResponse(errorMsg, false));
    }

    /**
     * md5 password here
     * @param password
     * @return
     */
    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static String bindingError(BindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            System.out.println(fieldError.getField() + "::" + fieldError.getDefaultMessage());
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        CommonResponse response = new CommonResponse(false);
        response.setData(errorMap);
        return new Gson().toJson(response);
    }

}
