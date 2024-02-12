//: ssia.domain.model.Employee.java


package ssia.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
//@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class Employee {

    String name;
    List<String> books;
    List<String> roles;

}///:~