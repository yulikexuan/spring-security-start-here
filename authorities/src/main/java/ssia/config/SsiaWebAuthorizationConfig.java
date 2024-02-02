//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    static final String AUTHORITIES = """
            hasAuthority('READ') && !hasAuthority('WRITE')
            """;

    @Bean
    AuthorizationManager<RequestAuthorizationContext> authorizationManager() {
        return new WebExpressionAuthorizationManager(AUTHORITIES);
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            AuthorizationManager<RequestAuthorizationContext> authorizationManager)
            throws Exception {

//        Simple Solutions :
//
//        http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(c -> c.anyRequest()
//                        .hasAuthority("write"));
//                        // .hasAnyAuthority("write", "read"));
//
//        http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(c -> c.anyRequest()
//                        // .hasAuthority("write"));
//                        .hasAnyAuthority("write", "read"));

        // Powerful Solution
        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c.anyRequest()
                        .access(authorizationManager));

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~