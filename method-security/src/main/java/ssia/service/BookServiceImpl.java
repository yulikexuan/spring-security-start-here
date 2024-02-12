//: ssia.service.BookServiceImpl.java


package ssia.service;


import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import ssia.domain.model.Employee;


@NoArgsConstructor
class BookServiceImpl implements BookService {

    @Override
    @PostAuthorize("returnObject.roles.contains('MANAGER')")
    public Employee bookDetails(String username) {
        return RECORDS.getOrDefault(username, null);
    }

}///:~