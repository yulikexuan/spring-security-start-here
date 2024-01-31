package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "home.html";
    }

    @GetMapping("/error")
    public String error() {
        return "error.html";
    }

    @GetMapping("/writing")
    public String writing() {
        return "writing.html";
    }

}