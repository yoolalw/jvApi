package controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@Controller
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello(){
        return "Hello.";
    }
}
