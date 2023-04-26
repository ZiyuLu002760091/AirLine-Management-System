package neu.edu.AirLinePortal.controller;

import jakarta.validation.Valid;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.entities.UserCredentialUpdate;
import neu.edu.AirLinePortal.entities.UserUpdate;
import neu.edu.AirLinePortal.service.UserService;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author YUlia
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/all", produces = "application/json")
    public ResponseEntity<String> findAllUser() {
        return ResponseEntity.ok(CommonUtils.success(userService.findAllUsers()));
    }

    @PostMapping(path = "/user/updateCredential", produces = "application/json")
    public ResponseEntity<String> updateUserCredential(@RequestBody @Valid UserCredentialUpdate user, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if (!validateSuccessful) { //not succeed
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            User update = userService.updateCredential(user);
            return ResponseEntity.ok(CommonUtils.success(update));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
    }

    @PostMapping(path = "/user/update", produces = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserUpdate user, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if (!validateSuccessful) { //not succeed
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            User update = userService.update(user);
            return ResponseEntity.ok(CommonUtils.success(update));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
    }


    @Deprecated
    @PostMapping(path = "/user/create", produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        boolean validateSuccessful = !bindingResult.hasErrors();
        if (!validateSuccessful) { //not succeed
            return ResponseEntity.badRequest().body(CommonUtils.bindingError(bindingResult));
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonUtils.failed(e.getMessage()));
        }
        return ResponseEntity.ok(CommonUtils.success());
    }

}
