//: ssia.web.controller.ProductController.java


package ssia.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{code}")
    String productCode(@PathVariable("code") String code) {
        return ">>> Product Code: %s".formatted(code);
    }

}///:~