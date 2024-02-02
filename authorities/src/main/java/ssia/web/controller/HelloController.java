package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello! Let's Read something ...";
    }

    @GetMapping("/writing")
    public String writing() {
        return "Hello! Let's Write something ...";
    }

}