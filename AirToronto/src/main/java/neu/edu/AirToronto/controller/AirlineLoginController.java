package neu.edu.AirToronto.controller;

import neu.edu.AirToronto.entities.LoginEntity;
import neu.edu.AirToronto.entities.User;
import neu.edu.AirToronto.service.AirlineLoginService;
import neu.edu.common.utils.CommonUtils;
import org.apache.coyote.Response;
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
@CrossOrigin
@RestController
public class AirlineLoginController {

    @Autowired
    private AirlineLoginService loginService;

    @PostMapping(path = "/airline/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginEntity loginEntity) {
        User user = loginService.login(loginEntity);
        if (null != user) {
            user.setPassword("***");
            return ResponseEntity.ok(CommonUtils.success(user));
        } else {
            return ResponseEntity.badRequest().body(CommonUtils.failed("User not found"));
        }
    }
}
