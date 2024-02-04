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


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(@NonNull final HttpSecurity http)
            throws Exception {

        http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(c -> c
                .requestMatchers(HttpMethod.POST, "/a")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/a/b")
                .hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/a/b/c")
                .hasAnyRole("ADMIN", "MANAGER")
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