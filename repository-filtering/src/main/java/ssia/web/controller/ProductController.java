//: ssia.web.controller.ProductController.java


package ssia.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.Product;
import ssia.repository.ProductRepository;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products/{text}")
    public List<Product> findAllProductsContaining(
            @PathVariable("text") String text) {

        return this.productRepository.findProductByNameContains(text);
    }

}///:~