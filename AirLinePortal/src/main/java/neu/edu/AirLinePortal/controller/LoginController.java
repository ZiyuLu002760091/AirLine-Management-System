package neu.edu.AirLinePortal.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.service.LoginService;
import neu.edu.AirLinePortal.service.UserService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping (path = "/login",produces = "application/json")
    public ResponseEntity<String> LoginUsingEmailAndPassword(@RequestBody User user, HttpServletRequest request) {
        boolean loginSuccess = loginService.login(user.getEmail(),user.getPassword(),request);
        if(!loginSuccess) {
            return ResponseEntity.badRequest().body(CommonUtils.failed("Username or password not found"));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

    @PostMapping(path = "/logincheck", produces = "application/json")
    public ResponseEntity<String> isLogin(HttpServletRequest request) {
        if(loginService.isLogin(request)) {
            return ResponseEntity.ok(CommonUtils.success());
        } else {
            return ResponseEntity.badRequest().body("not login");
        }
    }



}
