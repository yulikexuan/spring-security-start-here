//: ssia.config.SsiaRegisteredClientsConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.*;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;


@Configuration
class SsiaRegisteredClientsConfig {

    static final String CLIENT_REGISTRATION_ID = UUID.randomUUID().toString();

    static final String CLIENT_ID = "client";

    static final String CLIENT_SECRET = "secret";

    static final int ACCESS_TOKEN_TIME_TO_LIVE_HOURS = 12;

    /*
     * OAuth2TokenFormat.SELF_CONTAINED format is for generating NON-OPAQUE tokens
     * OAuth2TokenFormat.REFERENCE format is for generating OPAQUE tokens
     */
    @Bean
    OAuth2TokenFormat oauth2TokenFormat() {
        return OAuth2TokenFormat.SELF_CONTAINED;
        // return OAuth2TokenFormat.REFERENCE;
    }

    /*
     * The default active time for a token is 300 seconds
     * In examples, prefer to make the token life longer
     * Otherwise, will not have enough time to use the token for tests
     * Prefer to make it very large for example purposes
     * (like 12 hours in this case), but remember never to configure it this
     * large for a real-world app
     *
     * In a real app, go with a time to live from 10 to 30 minutes max
     */
    @Bean
    TokenSettings tokenSettings(OAuth2TokenFormat oauth2TokenFormat) {
        return TokenSettings.builder()
                .accessTokenFormat(oauth2TokenFormat)
                .accessTokenTimeToLive(Duration.ofHours(
                        ACCESS_TOKEN_TIME_TO_LIVE_HOURS))
                .build();
    }

    /*
     * Use RegisteredClientRepository to manage Clients' Details
     * RegisteredClientRepository works similarly to the UserDetailsService
     * RegisteredClientRepository is designed to retrieve clients' details
     * Use RegisteredClient object to describe a Client App that the
     * authorization server knows
     *
     * RegisteredClient is for clients what UserDetails is for users
     * RegisteredClientRepository works for client details like a
     * UserDetailsService works for the users' details
     *
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(
            TokenSettings tokenSettings) {

        /*
         * Only uses the authorization code grant type
         * But you can have clients that use multiple grant types
         * For example:
         *   - the authorization code
         *   - client credentials
         *   - or the refresh token grant types
         *
         * Similarly, by repeatedly calling the redirectUri() method
         * you can specify multiple allowed redirect URIs
         *
         * In a similar way, a client might also have access to multiple scopes
         * as well
         *
         * Preferably, you shouldn’t have a client being able to use both
         * a user-dependent grant type (such as the authorization code),
         * and a client-independent one (such as the client credentials)
         *
         * In a real-world app, the app would keep all these details in a
         * database from where your RegisteredClientRepository custom
         * implementation would retrieve them
         */
        RegisteredClient registeredClient =
                // A unique internal ID
                // Uniquely identifies the client and has a purpose only in
                // the internal app processes
                RegisteredClient.withId(CLIENT_REGISTRATION_ID)
                // A client ID - external client identifier
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET) // password
                // How the authorization server expects the client to authenticate
                // when sending requests for access tokens
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                // Authorization grant type – A grant type allowed by the
                // authorization server for this client
                // A client might use multiple grant types
                /*
                 * Preferably, you shouldn’t have a client being able to use both
                 * a user-dependent grant type (such as the authorization code),
                 * and a client-independent one (such as the client credentials)
                 */
                .authorizationGrantType(AUTHORIZATION_CODE)
                .authorizationGrantType(CLIENT_CREDENTIALS)
                //.authorizationGrantType(REFRESH_TOKEN)
                // Redirect URI – One of the URI addresses the authorization
                // server allows the client to request a redirect for providing
                // the authorization code in case of the authorization code
                // grant type
                .tokenSettings(tokenSettings)
                .redirectUri("https://www.manning.com/authorized")
                // A scope – Defines a purpose for the request of an access token
                // The scope can be used later in authorization rules
                .scope(OPENID)
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

}///:~