//: ssia.web.controller.ResourceController.java


package ssia.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ResourceController {

    @GetMapping("/resource/demo")
    public String resource() {
        return ">>> Hello, welcome to Resource Library!";
    }

}///:~