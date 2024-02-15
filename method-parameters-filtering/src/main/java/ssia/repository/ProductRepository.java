//: ssia.repository.ProductRepository.java


package ssia.repository;


import com.google.common.collect.Lists;
import ssia.domain.model.Product;

import java.util.List;
import java.util.function.Supplier;


public class ProductRepository {

    private ProductRepository() {}

    // This List of Products should NOT be immutable
    public static final Supplier<List<Product>> PRODUCTS_SUPPLIER =
            () -> Lists.newArrayList(
                    Product.of("Coffee Bean", "yul"),
                    Product.of("Candy", "joel"),
                    Product.of("Book", "zac"));

}///:~