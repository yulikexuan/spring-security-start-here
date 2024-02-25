//: ssia.config.RegisteredClients.java


package ssia.config;


import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.List;
import java.util.UUID;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;


class RegisteredClients {

    static final String CLIENT_REGISTRATION_ID = UUID.randomUUID().toString();

    static final String CLIENT_YUL_REGISTRATION_ID = UUID.randomUUID().toString();

    static final String RESOURCE_SERVER_REGISTRATION_ID = UUID.randomUUID().toString();

    static final String OAUTH2_CLIENT_REGISTRATION_ID = UUID.randomUUID().toString();

    static final String CLIENT_ID = "client";

    static final String CLIENT_YUL_ID = "client_yul";

    static final String RESOURCE_SERVER_ID = "resource_server";

    static final String OAUTH2_CLIENT_ID = "oauth2_client";

    static final String CLIENT_SECRET = "secret";

    static final String CLIENT_YUL_SECRET = "yul_secret";

    static final String RESOURCE_SERVER_SECRET = "resource_server_secret";

    static final String OAUTH2_CLIENT__SECRET = "oauth2_client_secret";

    static final String MANNING_REDIRECT_URI = "https://www.manning.com/authorized";

    static final String YUL_REDIRECT_URI = "http://localhost:8088/login/oauth2/code/yulauthorized";

    private RegisteredClients() {}

    static List<RegisteredClient> registeredClients(
            @NonNull final TokenSettings tokenSettings) {

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
        final var registeredClient =
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
                        .redirectUri(MANNING_REDIRECT_URI)
                        // A scope – Defines a purpose for the request of an access token
                        // The scope can be used later in authorization rules
                        .scope(OPENID)
                        .build();

        final var registeredClientYul = RegisteredClient
                .withId(CLIENT_YUL_REGISTRATION_ID)
                .clientId(CLIENT_YUL_ID)
                .clientSecret(CLIENT_YUL_SECRET)
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                .authorizationGrantType(AUTHORIZATION_CODE)
                .redirectUri(YUL_REDIRECT_URI)
                .scope(OPENID)
                .build();

        final var resourceServer =
                RegisteredClient.withId(RESOURCE_SERVER_REGISTRATION_ID)
                        .clientId(RESOURCE_SERVER_ID)
                        .clientSecret(RESOURCE_SERVER_SECRET)
                        .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                        .authorizationGrantType(CLIENT_CREDENTIALS)
                        .build();

        final var oauth2Client =
                RegisteredClient.withId(OAUTH2_CLIENT_REGISTRATION_ID)
                        .clientId(OAUTH2_CLIENT_ID)
                        .clientSecret(OAUTH2_CLIENT__SECRET)
                        .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                        .authorizationGrantType(CLIENT_CREDENTIALS)
                        .scope(OPENID)
                        .build();

        return List.of(registeredClient,
                registeredClientYul,
                resourceServer,
                oauth2Client);
    }

}///:~