//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import jakarta.servlet.Filter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ssia.web.filter.AuthenticationLoggingFilter;
import ssia.web.filter.RequestValidationFilter;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    @Value("${ssia.request.header.name}")
    private String headerName;

    @Bean
    Filter requestValidationFilter() {
        return RequestValidationFilter.of(headerName);
    }

    @Bean
    Filter authenticationLoggingFilter() {
        return AuthenticationLoggingFilter.of(headerName);
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull final Filter requestValidationFilter,
            @NonNull final Filter authenticationLoggingFilter)
            throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authenticationLoggingFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~