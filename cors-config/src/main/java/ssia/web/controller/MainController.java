//: ssia.web.controller.MainController.java


package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main.html";
    }

    @ResponseBody
    @PostMapping("/test")
    // When using CorsConfigurationSource to config CORS,
    // this post is permitted for CORS
    public String test() {
        log.info(">>> Test method was just called! ");
        return "NOT FOR CORS without @CrossOrigin!";
    }

    @ResponseBody
    @PostMapping("/cors/test")
    // It's better to use CorsConfigurationSource to config CORS in only one place
    // @CrossOrigin(origins = "http://localhost:7070")
    public String testCors() {
        log.info(">>> CORS Test method was just called! ");
        return "HELLO, CORS PERMITTED HERE !";
    }

}///:~