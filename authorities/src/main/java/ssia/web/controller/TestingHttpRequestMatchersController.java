package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestingHttpRequestMatchersController {

    @PostMapping("/a")
    public String postEndpointA() {
        return ">>> POST /a Works! ";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return ">>> GET /a Works! ";
    }

    @GetMapping("/a/b")
    public String getEnpointAB() {
        return ">>> GET /a/b Works! ";
    }

    @GetMapping("/a/b/c")
    public String getEnpointABC() {
        return ">>> GET /a/b/c Works! ";
    }

    @PutMapping("/a")
    public String putA() {
        return ">>> PUT /a Works! ";
    }

}