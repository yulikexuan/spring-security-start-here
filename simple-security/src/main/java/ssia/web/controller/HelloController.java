package ssia.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();

        return "Hello %s !  ".formatted(authentication.getName());
    }

    @GetMapping("/bonjour")
    public String bonjour(Authentication authentication) {
        return "Bonjour %s !  ".formatted(authentication.getName());
    }

}