//: ssia.web.controller.HelloController.java


package ssia.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    // Requires this HTTP GET method to obtain the CSRF token
    @GetMapping("/hello")
    public String getHello(HttpServletRequest request) {
        return ">>> GET - Hello! [TOKEN]: %s".formatted(this.token(request));
    }

    @PostMapping("/hello")
    public String postHello(HttpServletRequest request) {
        return ">>> POST - Hello! [TOKEN]: %s".formatted(this.token(request));
    }

    @PostMapping("/ciao")
    public String postCiao(HttpServletRequest request) {
        return ">>> POST - Ciao! [TOKEN]: %s".formatted(this.token(request));
    }

    private String token(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        return token == null ? "N/A" : token.getToken();
    }

}///:~