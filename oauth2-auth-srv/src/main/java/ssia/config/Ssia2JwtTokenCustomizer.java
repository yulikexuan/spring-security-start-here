//: ssia.config.Ssia2JwtTokenCustomizer.java


package ssia.config;


import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import static org.springframework.security.oauth2.server.authorization.OAuth2TokenType.ACCESS_TOKEN;


class Ssia2JwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext jwtEncodingContext) {

        final OAuth2TokenType tokenType = jwtEncodingContext.getTokenType();
        final JwtClaimsSet.Builder claimsBuilder = jwtEncodingContext.getClaims();

        if (ACCESS_TOKEN.equals(tokenType)) {

            final var authorities = jwtEncodingContext.getPrincipal()
                    .getAuthorities();

            final var roles = AuthorityUtils.authorityListToSet(authorities)
                    .stream()
                    .map(a -> a.replaceFirst("^ROLE_", ""))
                    .collect(ImmutableSet.toImmutableSet());

            claimsBuilder.claims(claims -> {
                claims.put("roles", roles);
                claims.put("priority", "HIGH");
            });
        }

    }

}///:~