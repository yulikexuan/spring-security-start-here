//: ssia.service.ProductServiceImpl.java


package ssia.service;


import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import ssia.domain.model.Product;

import java.util.List;

import static ssia.repository.ProductRepository.PRODUCTS_SUPPLIER;


public class ProductServiceImpl implements ProductService {

    @Override
    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(@NonNull final List<Product> products) {

        // Ignore code selling products and return the sold products list

        return ImmutableList.copyOf(products);
    }

    @Override
    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> findAll() {
        return PRODUCTS_SUPPLIER.get();
    }

}///:~