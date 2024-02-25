//: ssia.web.controller.HomeController.java


package ssia.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    @GetMapping("/home")
    String home() {
        return "index.html";
    }

}///:~