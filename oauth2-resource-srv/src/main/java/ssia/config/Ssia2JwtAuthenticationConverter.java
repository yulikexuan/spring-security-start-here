//: ssia.config.Ssia2JwtAuthenticationConverter.java


package ssia.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;


class Ssia2JwtAuthenticationConverter
        implements Converter<Jwt, Ssia2JwtToken> {

    @Override
    public Ssia2JwtToken convert(Jwt jwt) {

        List<GrantedAuthority> authorities = List.of(() -> "ROLE_ADMIN");

        String priority = String.valueOf(jwt.getClaims().get("priority"));

        return Ssia2JwtToken.of(jwt, authorities, priority);
    }

}///:~