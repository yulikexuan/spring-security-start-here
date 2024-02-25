//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http) throws Exception {

        return http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c
                        .requestMatchers(HttpMethod.GET, "/codes/challenge")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .denyAll()
                ).build();
    }

}///:~