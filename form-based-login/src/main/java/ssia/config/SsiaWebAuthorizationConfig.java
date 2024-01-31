//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SsiaAuthenticationSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SsiaAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull final AuthenticationSuccessHandler authenticationSuccessHandler,
            @NonNull final AuthenticationFailureHandler authenticationFailureHandler)
            throws Exception {

        http.formLogin(c -> c.successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler))
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~