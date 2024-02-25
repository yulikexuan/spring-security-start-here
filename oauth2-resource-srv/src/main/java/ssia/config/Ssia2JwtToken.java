//: ssia.config.Ssia2Authentication.java


package ssia.config;


import lombok.Getter;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;


@Getter
class Ssia2JwtToken extends JwtAuthenticationToken {

    private final String priority;

    private Ssia2JwtToken(
            Jwt jwt,
            Collection<? extends GrantedAuthority> authorities,
            String priority) {

        super(jwt, authorities);

        this.priority = priority;
    }

    public static Ssia2JwtToken of(
            @NonNull final Jwt jwt,
            @NonNull final Collection<? extends GrantedAuthority> authorities,
            @NonNull final String priority) {

        return new Ssia2JwtToken(jwt, authorities, priority);
    }

}///:~