package neu.edu.AirToronto.controller;

import neu.edu.common.utils.CommonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class HelloWorld {
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello";
    }

    @GetMapping(path = "/hello2")
    public ResponseEntity<String> helloWorld2() {
        return ResponseEntity.ok(CommonUtils.success("Hello"));
    }
}
