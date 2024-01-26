//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ssia.config.security.UsernameAuthenticationProvider;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"ssia.security", "ssia.web.controller", "ssia.web.filter"})
class SsiaWebAuthorizationConfig {

    @Bean
    AuthenticationProvider authenticationProvider(
            @NonNull final UserDetailsService userDetailsService,
            @NonNull final PasswordEncoder passwordEncoder) {

        return UsernameAuthenticationProvider.of(
                userDetailsService, passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull final AuthenticationProvider authenticationProvider)
            throws Exception {

        http.httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~