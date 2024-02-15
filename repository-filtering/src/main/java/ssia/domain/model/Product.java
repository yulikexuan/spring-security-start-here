//: ssia.domain.model.Product.java


package ssia.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Product {

    @Id
    private int id;

    private String name;

    private String owner;

}///:~