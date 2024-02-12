//: ssia.service.NameService.java


package ssia.service;


import java.util.List;
import java.util.Map;


public interface NameService {

    static final Map<String, List<String>> SECRET_NAMES = Map.of(
            "yul", List.of("Energico", "Perfecto"),
            "joel", List.of("Fantastico"),
            "zac", List.of("Blossom", "Shuggy", "Angel"));

    String name();

    List<String> secretNames(String name);

    static NameService authorizedNameService() {
        return new AuthorizedNameService();
    }

}

///:~