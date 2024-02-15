//: ssia.repository.ProductRepository.java


package ssia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ssia.domain.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /*
     * Using @PostFilter is NOT a Good Solution
     *
     * Instead, Provides SpEL expressions directly in the queries
     */
    // @PostFilter("filterObject.owner == authentication.principal.username")
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:text% AND p.owner=?#{authentication.principal.username}")
    List<Product> findProductByNameContains(String text);

}///:~