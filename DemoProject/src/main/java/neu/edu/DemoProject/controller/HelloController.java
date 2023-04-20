package neu.edu.DemoProject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/helloWorld")
    @CrossOrigin
    public String helloWorld() {
        return "HelloWorld";
    }

}
