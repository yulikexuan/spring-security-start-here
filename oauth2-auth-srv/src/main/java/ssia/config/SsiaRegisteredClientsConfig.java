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

        return new InMemoryRegisteredClientRepository(
                RegisteredClients.registeredClients(tokenSettings));
    }

}///:~