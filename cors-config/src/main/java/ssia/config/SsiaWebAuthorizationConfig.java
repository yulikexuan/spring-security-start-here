//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"ssia.web"})
class SsiaWebAuthorizationConfig {

    static final List<String> CORS_ALLOWED_ORIGINS = List.of(
            "http://localhost:7070");
    static final List<String> CORS_ALLOWED_HTTP_METHODS = List.of("GET", "POST");
    static final List<String> CORS_ALLOWED_HEADERS = List.of("*");

    @Bean
    CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(CORS_ALLOWED_ORIGINS);
        corsConfig.setAllowedMethods(CORS_ALLOWED_HTTP_METHODS);
        corsConfig.setAllowedHeaders(CORS_ALLOWED_HEADERS);
        return corsConfig;
    }

    @Bean
    Customizer<CorsConfigurer<HttpSecurity>> corsConfigurerCustomizer(
            CorsConfiguration corsConfiguration) {

        return corsConfigurer ->
                corsConfigurer.configurationSource(request -> corsConfiguration);
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull final Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer)
            throws Exception {

        // To replace @CrossOrigin(origins = "http://localhost:7070") in handlers
        http.cors(corsCustomizer)
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authHttpRequestsConfigurer ->
                        authHttpRequestsConfigurer.anyRequest().permitAll());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~