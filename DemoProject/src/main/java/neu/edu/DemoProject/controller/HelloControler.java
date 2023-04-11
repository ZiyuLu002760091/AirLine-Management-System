package neu.edu.DemoProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */
@RestController
public class HelloControler {

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "HelloWorld";
    }

}
