//: ssia.web.controller.ControllerAdvice.java


package ssia.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleInsufficientFunds(RuntimeException e) {
        return ResponseEntity.badRequest().body(
                ">>> Error Name: %s%n>>> Error Message: %s".formatted(
                        e.getClass().getSimpleName(), e.getMessage()));
    }

}///:~