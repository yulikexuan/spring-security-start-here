//: ssia.web.controller.HelloController.java


package ssia.web.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ssia.service.NameService;

import java.util.List;


@RestController
@AllArgsConstructor
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String hello() {
        return ">>> Hello, %s".formatted(this.nameService.name());
    }

    @GetMapping("/secret/names/{name}")
    public List<String> secretNames(@PathVariable("name") String name) {
        return this.nameService.secretNames(name);
    }

}///:~