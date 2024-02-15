//: ssia.domain.model.Product.java


package ssia.domain.model;


import lombok.NonNull;


public record Product(String name, String owner) {

    public static Product of(
            @NonNull final String name, @NonNull final String owner) {
        return new Product(name, owner);
    }

}///:~