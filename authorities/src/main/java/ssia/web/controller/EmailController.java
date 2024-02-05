//: ssia.web.controller.EmailController.java


package ssia.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
class EmailController {

    @GetMapping("/email/{email}")
    String echoTecsysEmail(@PathVariable("email") String email) {
        return ">>> Your Email is %s ".formatted(email);
    }

}///:~