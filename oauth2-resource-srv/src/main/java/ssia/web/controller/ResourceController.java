//: ssia.web.controller.ResourceController.java


package ssia.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.ResourceAuthentication;


@RestController
class ResourceController {

    @GetMapping("/resource/demo")
    public ResponseEntity<ResourceAuthentication> resource(
            @NonNull Authentication authentication) {

        var resAuth = ResourceAuthentication.of(
                ">>> Hello, welcome to Resource Library!",
                authentication);

        return new ResponseEntity<>(resAuth, HttpStatus.OK);
    }

}///:~