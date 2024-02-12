//: ssia.service.AuthorizedNameService.java


package ssia.service;


import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


public class AuthorizedNameService implements NameService {

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String name() {
        return "Fantastico";
    }

    /*
     * Uses #name to represent the value of the method parameters in the
     * authorization expression
     */
    @Override
    @PreAuthorize("#name == authentication.principal.username")
    public List<String> secretNames(String name) {
        return SECRET_NAMES.getOrDefault(name, List.of());
    }

}///:~