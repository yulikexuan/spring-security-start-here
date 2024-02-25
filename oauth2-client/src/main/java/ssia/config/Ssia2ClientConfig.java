//: ssia.config.Ssia2ClientConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;


@Configuration
public class Ssia2ClientConfig {

    public static final String OAUTH2_CLIENT_REGISTRATION_ID = UUID.randomUUID().toString();

    public static final String OAUTH2_CLIENT_ID = "oauth2_client";

    static final String OAUTH2_CLIENT_SECRET = "oauth2_client_secret";
    static final String AUTHORIZATION_TOKEN_URI = "http://127.0.0.1:7777/oauth2/token";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {

        return httpSecurity
                .oauth2Client(Customizer.withDefaults())
                .authorizeHttpRequests(requestConfig ->
                        requestConfig.anyRequest().permitAll())
                .build();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        var oauth2ClientRegistration = ClientRegistration
                .withRegistrationId(OAUTH2_CLIENT_REGISTRATION_ID)
                .clientId(OAUTH2_CLIENT_ID)
                .clientSecret(OAUTH2_CLIENT_SECRET)
                .authorizationGrantType(CLIENT_CREDENTIALS)
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                .tokenUri(AUTHORIZATION_TOKEN_URI)
                .scope(OPENID)
                .build();

        return new InMemoryClientRegistrationRepository(
                oauth2ClientRegistration);
    }

    @Bean
    OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {

        var provider = OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();

        var clientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository,
                auth2AuthorizedClientRepository);

        clientManager.setAuthorizedClientProvider(provider);

        return clientManager;
    }

}///:~