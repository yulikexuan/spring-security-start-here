//: ssia.web.controller.BookController.java


package ssia.web.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.Employee;
import ssia.service.BookService;


@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/details/{username}")
    public Employee employeeDetails(@PathVariable("username") String username) {
        return this.bookService.bookDetails(username);
    }

}///:~