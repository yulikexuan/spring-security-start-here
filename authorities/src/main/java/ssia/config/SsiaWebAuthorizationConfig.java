//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    static final String AUTHORIZATION_EXPRESSION = """
            T(java.time.LocalTime).now().isBefore(T(java.time.LocalTime).of(12, 0))
            """;

    @Bean
    public SecurityFilterChain filterChain(@NonNull final HttpSecurity http)
            throws Exception {

//        http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(c -> c.anyRequest()
//                        .hasRole("ADMIN"));

//        http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(c -> c.anyRequest().hasAnyRole(
//                        "ADMIN", "EMPLOYEE"));

        http.authorizeHttpRequests(
                c -> c.anyRequest().access(new WebExpressionAuthorizationManager(
                        AUTHORIZATION_EXPRESSION))
        );

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~