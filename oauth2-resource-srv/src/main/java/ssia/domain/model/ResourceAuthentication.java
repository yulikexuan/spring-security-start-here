//: ssia.domain.model.ResourceAuthentication.java


package ssia.domain.model;


import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;


public record ResourceAuthentication(String msg, Authentication authentication) {

    public static ResourceAuthentication of(
            @NonNull final String msg,
            @NonNull final Authentication authentication) {

        return new ResourceAuthentication(msg, authentication);
    }

}///:~