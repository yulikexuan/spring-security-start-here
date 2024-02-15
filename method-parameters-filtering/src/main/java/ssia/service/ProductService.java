//: ssia.service.ProductService.java


package ssia.service;


import ssia.domain.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> sellProducts(List<Product> products);

    List<Product> findAll();

    static ProductService of() {
        return new ProductServiceImpl();
    }

}///:~