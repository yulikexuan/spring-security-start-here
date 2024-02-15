//: ssia.web.controller.ProductController.java


package ssia.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.Product;
import ssia.service.ProductService;

import java.util.List;

import static ssia.repository.ProductRepository.PRODUCTS_SUPPLIER;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProducts() {
        return this.productService.sellProducts(PRODUCTS_SUPPLIER.get());
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return this.productService.findAll();
    }

}///:~