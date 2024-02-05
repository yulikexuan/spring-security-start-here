//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import java.util.List;

import static ssia.config.SsiaUserManagementConfig.ALL_ROLES;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    /*
     * Any authenticated user can see the video content if the request comes
     * from the US, Canada, or the UK, or if they use English
     */
    static final String VIDEO_URL_PATTERN = ".*/(us|uk|ca)+/(en|fr).*";
    static final String EMAIL_PATTERN = ".*(?:.+@tecsys\\.com)";

    @Bean
    RegexRequestMatcher videoRequestMatcher() {
        return new RegexRequestMatcher(VIDEO_URL_PATTERN, HttpMethod.GET.name());
    }

    @Bean
    RegexRequestMatcher emailRequestMatcher() {
        return new RegexRequestMatcher(EMAIL_PATTERN, HttpMethod.GET.name());
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull RegexRequestMatcher videoRequestMatcher,
            @NonNull RegexRequestMatcher emailRequestMatcher)
            throws Exception {

        http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(c -> c
                .requestMatchers(HttpMethod.GET, "/a/b/**")
                .hasAnyRole("MANAGER", "ADMIN")
                // Deny all requests that use a value for the path variable
                // that has anything else other than digits
                .requestMatchers(HttpMethod.GET, "/products/{code:^[0-9]*$}")
                .hasAnyRole(ALL_ROLES.toArray(String[]::new))
                .requestMatchers(videoRequestMatcher)
                .hasRole("ADMIN")
                .requestMatchers(emailRequestMatcher)
                .hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/a")
                .permitAll()
                .anyRequest()
                .denyAll());

        http.csrf(CsrfConfigurer::disable);

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~