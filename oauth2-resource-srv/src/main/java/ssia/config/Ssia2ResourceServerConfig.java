//: ssia.config.ResourceServerConfig.java


package ssia.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class Ssia2ResourceServerConfig {

    @Value("${oauth2.introspection.uri}")
    private String introspectionUri;

    @Value("${oauth2.resource.server.client.id}")
    private String resourceClientId;

    @Value("${oauth2.resource.server.client.secret}")
    private String resourceClientSecret;

    @Value("${oauth2.key.set.uri}")
    private String keySetUri;

    @Bean
    Converter<Jwt, Ssia2JwtToken> jwtConverter() {
        return new Ssia2JwtAuthenticationConverter();
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            Converter<Jwt, Ssia2JwtToken> jwtConverter)
            throws Exception {

        // Config the Authentication for this Resource Server
        // Spring Security only Supports JWTs or Opaque Tokens
        // Not Both At The Same Time
        return httpSecurity.oauth2ResourceServer(
                resSrvCfg -> resSrvCfg
                        .jwt(jwt -> jwt.jwkSetUri(keySetUri)
                                .jwtAuthenticationConverter(jwtConverter))
//                        .opaqueToken(opaqueToken -> opaqueToken
//                                .introspectionUri(introspectionUri)
//                                .introspectionClientCredentials(
//                                        resourceClientId,
//                                        resourceClientSecret))
                ).authorizeHttpRequests(reqMatcherReg ->
                        reqMatcherReg.anyRequest().authenticated())
                .build();
    }

//    @Bean
//    AuthenticationManagerResolver<HttpServletRequest> authManagerResolver() {
//        return fromTrustedIssuers("http://localhost:7777", "http://localhost:8888");
//    }

}///:~