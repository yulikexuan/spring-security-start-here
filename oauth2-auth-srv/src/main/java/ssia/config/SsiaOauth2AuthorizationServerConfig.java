//: ssia.config.Oauth2AuthorizationServerConfig.java


package ssia.config;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Configuration
class SsiaOauth2AuthorizationServerConfig {

    @Value("${login.form.url}")
    private String loginFormUrl;

    /*
     * 1. The Configuration Filter for Protocol Endpoints
     */
    @Bean
    @Order(1)
    SecurityFilterChain asFilterChain(HttpSecurity http) throws Exception {

        /*
         * applyDefaultSecurity is a utility method we use to define a minimal
         * set of configurations,  you can override later if needed
         */
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Enable OpenID Connect Protocol
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        /*
         * Specifying the authentication page for users
         * Unless configuring a custom one, this is the one used for the
         * authorization server configuration
         */
        http.exceptionHandling(e -> e.authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint(loginFormUrl))
        );

        return http.build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return new Ssia2JwtTokenCustomizer();
    }

    /*
     * 2. Configures authentication and authorization
     *    - Enabling the form login authentication so the app gives the user a
     *      simple login page to authenticate
     *    - Specify that the app only allows access to authenticated users to
     *      any endpoints
     *
     * Other configurations you could write here, besides authentication and
     * authorization, could be for specific protection mechanisms such as
     * CSRF or CORS
     */
    @Bean
    // Sets the filter to be interpreted after the protocol endpoints one @Order(1)
    @Order(2)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        // We enable the form login authentication method
        return http.formLogin(Customizer.withDefaults())
                 // We configure all endpoints to require authentication
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .build();
    }

    /*
     * Configure Key Pair Management
     * The authorization server uses Private Keys to sign the tokens
     * and provides the clients with public keys they can use to validate
     * the tokens' authenticity
     *
     * The JWKSource is the object providing keys management for
     * the Spring Security authorization server
     *
     *  and add it to the set of keys the authorization server can use
     *
     * In a real-world app, the app would read the keys from a location where
     * they’re safely stored (such as a vault configured in the environment)
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {

        // Generating a public-private key pair programmatically using
        // the RSA Cryptographic Algorithm
        final var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        // Adding the key pair to the set the authorization server uses to
        // sign the issued tokens
        JWKSet jwkSet = new JWKSet(rsaKey);

        // Wrapping the key set into a JWKSource implementation and returning
        // it to be added to the Spring Context
        return new ImmutableJWKSet<>(jwkSet);
    }

    /*
     * This object allows customizing all the endpoints paths that
     * the authorization server exposes
     *
     * The endpoints paths will get some defaults that we'll analyze later
     * in this section
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }


}///:~