//: ssia.web.controller.TestingCsrfController.java


package ssia.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/csrf")
public class TestingCsrfTokenController {

    @GetMapping("/hello")
    String getHello() {
        return ">>> Accessing GET ... ";
    }

    @PostMapping("/hello")
    String postHello() {
        return ">>> Accessing POST ...";
    }

}///:~