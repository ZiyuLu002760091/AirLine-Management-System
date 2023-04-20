package neu.edu.AirLinePortal.controller;

import jakarta.validation.Valid;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.entities.UserRequestEntity;
import neu.edu.AirLinePortal.service.RegisterService;
import neu.edu.AirLinePortal.service.UserService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;

    @PostMapping(path = "/register", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestEntity user, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if (!validateSuccessful) { //not succeed
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            String result = registerService.regiSuccess(user);
            if (null != result && !result.isEmpty()) {
                return ResponseEntity.badRequest().body(CommonUtils.failed(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

}
