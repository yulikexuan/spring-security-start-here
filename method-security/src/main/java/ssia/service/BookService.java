//: ssia.service.BookService.java


package ssia.service;


import ssia.domain.model.Employee;

import java.util.List;
import java.util.Map;


public interface BookService {

    Map<String, Employee> RECORDS = Map.of(
            "yul", Employee.of("Yu Li",
                    List.of("Spring Start Here", "Spring in Action 6th."),
                    List.of("ADMIN", "MANAGER")),
            "joel", Employee.of("Joel P.",
                    List.of("Beautiful Paris", "Karamazov Brothers"),
                    List.of("MANAGER", "EMPLOYEE")),
            "zac", Employee.of("Zac Ch.",
                    List.of("Karamazov Brothers", "Head First Java"),
                    List.of("EMPLOYEE")));

    static BookService of() {
        return new BookServiceImpl();
    }

    Employee bookDetails(String username);

}///:~