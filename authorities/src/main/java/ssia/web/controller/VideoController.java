//: ssia.web.controller.VideoController.java


package ssia.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VideoController {

    @GetMapping("/video/{country}/{language}")
    String show(@PathVariable("country") String country,
                @PathVariable("language") String language) {

        return ">>> Video allowed for %s %s".formatted(country, language);
    }

}///:~